package no.whirlwin.asteroidinspector.api.v1;

import no.whirlwin.asteroidinspector.api.integrations.nasaapi.v1.NasaApiAsteroidResponse;
import no.whirlwin.asteroidinspector.api.integrations.nasaapi.v1.NasaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AsteroidService {

    private final NasaApiService nasaApiService;
    private final AsteroidMapper asteroidMapper;
    private final AsteroidRepository asteroidRepository;
    private final CloseApproachRepository closeApproachRepository;

    @Autowired
    public AsteroidService(NasaApiService nasaApiService,
                           AsteroidMapper asteroidMapper,
                           AsteroidRepository asteroidRepository,
                           CloseApproachRepository closeApproachRepository) {
        this.nasaApiService = nasaApiService;
        this.asteroidMapper = asteroidMapper;
        this.asteroidRepository = asteroidRepository;
        this.closeApproachRepository = closeApproachRepository;
    }

    public List<Asteroid> findAsteroids(LocalDate fromDate, LocalDate toDate) {
        return asteroidRepository.findAsteroids(fromDate, toDate);
    }

    public Asteroid findAsteroid(int year) {
        return asteroidRepository.findLargestAsteroid(year);
    }

    @Scheduled(fixedDelay = 1000) // TODO every hour or so - rate limit api key
    public void syncAsteroidData() {
        NasaApiAsteroidResponse response = nasaApiService.getAsteroids();
        saveAsteroidData(response);
        String next = response.getLinks().getNext();
        while (next != null) {
            NasaApiAsteroidResponse res = nasaApiService.next(next, NasaApiAsteroidResponse.class);
            saveAsteroidData(res);
            next = res.getLinks().getNext();
        }
    }

    private void saveAsteroidData(NasaApiAsteroidResponse response) {
        response.getNearEarthObjects()
                .forEach(neo -> {
                    // TODO run in transactions
                    Asteroid asteroid = asteroidMapper.map(neo);
                    asteroidRepository.save(asteroid);
                    neo.getCloseApproachData()
                            .stream()
                            .map(ca -> asteroidMapper.map(ca, asteroid))
                            .forEach(closeApproachRepository::save);
                });
    }
}
