package no.whirlwin.asteroidinspector.api.v1;

import no.whirlwin.asteroidinspector.api.integrations.nasaapi.v1.NasaApiAsteroidResponse;
import org.springframework.stereotype.Component;

@Component
public class AsteroidMapper {

    Asteroid map(NasaApiAsteroidResponse.NearEarthObject neo) {
        return new Asteroid(
                neo.getId(),
                neo.getEstimatedDiameter().getMeters().getEstimatedDiameterMax()
        );
    }

    CloseApproach map(NasaApiAsteroidResponse.CloseApproach closeApproach, Asteroid asteroid) {
        return new CloseApproach(
                asteroid,
                closeApproach.getCloseApproachDate(),
                closeApproach.getMissDistance().getKilometers()
        );
    }
}
