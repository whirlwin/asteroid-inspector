package no.whirlwin.asteroidinspector.api.integrations.nasaapi.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NasaApiService {

    private final String NEO_BROWSE_URI;
    private final RestTemplate restTemplate;

    public NasaApiService(RestTemplate restTemplate, @Value("nasa.api_key") String nasaApiKey) {
        this.restTemplate = restTemplate;
        NEO_BROWSE_URI = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.nasa.gov")
                .path("/neo/rest/v1/neo/browse")
                .queryParam("api_key", nasaApiKey)
                .build()
                .toUriString();
    }

    public NasaApiAsteroidResponse getAsteroids() {
        return restTemplate.getForObject(NEO_BROWSE_URI, NasaApiAsteroidResponse.class);
    }

    public <T> T next(String url, Class<T> t) {
       return restTemplate.getForObject(url, t);
    }
}
