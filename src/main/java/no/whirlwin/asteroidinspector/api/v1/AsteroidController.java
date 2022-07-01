package no.whirlwin.asteroidinspector.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class AsteroidController {

    private final AsteroidService asteroidService;

    @Autowired
    public AsteroidController(AsteroidService asteroidService) {
        this.asteroidService = asteroidService;
    }

    // TODO improve RESTful URL design
    @GetMapping(value = "/v1/asteroids")
    @ResponseBody
    List<Asteroid> searchAsteroids(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                       @RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) {
        return asteroidService.findAsteroids(fromDate, toDate);
    }

    // TODO improve RESTful URL design
    @GetMapping(value = "/v1/asteroids/largest")
    @ResponseBody
    Asteroid getLargestAsteroid(@RequestParam("year") int year) {
        return asteroidService.findAsteroid(year);
    }

    // TODO improve error message
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
    })
    public Map<String, String> handleMethodArgumentNotValid(Exception e) {
        return Map.of("error", Objects.requireNonNull(e.getMessage()));
    }
}
