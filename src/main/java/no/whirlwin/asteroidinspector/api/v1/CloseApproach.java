package no.whirlwin.asteroidinspector.api.v1;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "close_approach")
public class CloseApproach {

    public CloseApproach() {
    }

    public CloseApproach(Asteroid asteroid, LocalDate date, double missDistanceKilometers) {
        this.asteroid = asteroid;
        this.date = date;
        this.missDistanceKilometers = missDistanceKilometers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asteroid_id")
    private Asteroid asteroid;

    private LocalDate date;

    private double missDistanceKilometers;
}
