package no.whirlwin.asteroidinspector.api.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AsteroidRepository extends JpaRepository<Asteroid, String> {

    @Query(value = "select * from (select distinct on (a.id) a.id, * from close_approach ca join asteroid a on ca.asteroid_id = a.id and ca.date >= ?1 and date < ?2) as closest order by closest.miss_distance_kilometers limit 10", nativeQuery = true)
    List<Asteroid> findAsteroids(LocalDate from, LocalDate to);

    @Query(value = "select * from close_approach ca join asteroid a on ca.asteroid_id = a.id and date_part('year', ca.date) = ?1 order by max_diameter desc limit 1", nativeQuery = true)
    Asteroid findLargestAsteroid(int year);
}
