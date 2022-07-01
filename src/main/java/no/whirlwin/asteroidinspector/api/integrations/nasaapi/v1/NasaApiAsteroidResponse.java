package no.whirlwin.asteroidinspector.api.integrations.nasaapi.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NasaApiAsteroidResponse {

    @JsonProperty("links")
    Links links;

    @JsonProperty("near_earth_objects")
    List<NearEarthObject> nearEarthObjects;

    public static class Links {

        public Links() {
        }

        @JsonProperty("next")
        private String next;

        public String getNext() {
            return next;
        }
    }

    public static class NearEarthObject {

        public NearEarthObject() {
        }

        @JsonProperty("id")
        private String id;

        @JsonProperty("estimated_diameter")
        private EstimatedDiameter estimatedDiameter;

        @JsonProperty("close_approach_data")
        List<CloseApproach> closeApproachData;

        public String getId() {
            return id;
        }

        public EstimatedDiameter getEstimatedDiameter() {
            return estimatedDiameter;
        }

        public List<CloseApproach> getCloseApproachData() {
            return closeApproachData;
        }
    }

    public static class EstimatedDiameter {

        public EstimatedDiameter() {
        }

        @JsonProperty("meters")
        private Meters meters;

        public static class Meters {

            public Meters() {
            }

            @JsonProperty("estimated_diameter_min")
            private String estimated_diameter_min;

            @JsonProperty("estimated_diameter_max")
            private double estimated_diameter_max;

            public double getEstimatedDiameterMax() {
                return estimated_diameter_max;
            }
        }

        public Meters getMeters() {
            return meters;
        }
    }

    public static class CloseApproach {

        public CloseApproach() {
        }

        @JsonProperty("close_approach_date")
        LocalDate closeApproachDate;

        @JsonProperty("miss_distance")
        MissDistance missDistance;

        public LocalDate getCloseApproachDate() {
            return closeApproachDate;
        }

        public MissDistance getMissDistance() {
            return missDistance;
        }

        public static class MissDistance {

            @JsonProperty("kilometers")
            double kilometers;

            public double getKilometers() {
                return kilometers;
            }
        }
    }

    public Links getLinks() {
        return links;
    }

    public List<NearEarthObject> getNearEarthObjects() {
        return nearEarthObjects;
    }
}


