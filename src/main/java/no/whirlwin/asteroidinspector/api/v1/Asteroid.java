package no.whirlwin.asteroidinspector.api.v1;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asteroid")
public class Asteroid {

    @Id
    private String id;

    private double maxDiameter;

    public Asteroid() {
    }

    public Asteroid(String id, double maxDiameter) {
        this.id = id;
        this.maxDiameter = maxDiameter;
    }

    public String getId() {
        return id;
    }

    public double getMaxDiameter() {
        return maxDiameter;
    }
}
