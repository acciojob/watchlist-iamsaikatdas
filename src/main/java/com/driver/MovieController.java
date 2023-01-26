package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("add_movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New movie added sucessfully", HttpStatus.CREATED);
    }

    @PostMapping("add_director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("New Director added sucessfully", HttpStatus.CREATED);
    }
    //************************************************
    @PutMapping("/add_movie_director_pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie, @RequestParam("director") String director){
        movieService.addMovieDirectorPair(movie, director);
        return new ResponseEntity<>("New movie-director added sucessfully", HttpStatus.CREATED);
    }

    @GetMapping("/get_movie_by_name/{movieName}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("movieName") String movieName){
        Movie movie = movieService.findMovieByName(movieName);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }
    @GetMapping("/get_director_by_name/{directorName}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("directorName") String directorName){
        Director director = movieService.getDirectorByName(directorName);
        return new ResponseEntity<>(director, HttpStatus.CREATED);
    }

    // ******************************************************************
    @GetMapping("/get_movies_by_director_name/{getMoviesByDirectorName}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("getMoviesByDirectorName") String getMoviesByDirectorName){
        List<String> movie = movieService.findMoviesFromDirector(getMoviesByDirectorName);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("/get_all_movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies = movieService.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @PostMapping("/delete_director_by_name/{deleteDirectorByName}")
    public ResponseEntity<String> deleteDirectorByName(@PathVariable("deleteDirectorByName") String deleteDirectorByName){
        movieService.deleteDirectorByName(deleteDirectorByName);
        return new ResponseEntity<>(deleteDirectorByName + " removed sucessfully", HttpStatus.CREATED);
    }

    @PostMapping("/delete_all_directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>(" All Director removed sucessfully", HttpStatus.CREATED);
    }

    @GetMapping("/get_all_director_details")
    public ResponseEntity<List<String>> getAllDirectorDetails(){
        List<String> directorDetailsAll = movieService.getAllDirectorDetails();
        return new ResponseEntity<>(directorDetailsAll, HttpStatus.CREATED);
    }
    @GetMapping("/get_all_movies_by_director")
    public ResponseEntity<List<String>> getAllMoviesByDirector(){
        List<String> details = movieService.getAllMoviesByDirector();
        return new ResponseEntity<>(details, HttpStatus.CREATED);
    }
}
