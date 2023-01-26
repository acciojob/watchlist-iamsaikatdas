package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public void addMovie(Movie movie){
        movieRepository.saveMovie(movie);
    }
    public void addDirector(Director director){
        movieRepository.saveDirector(director);
    }
    public void addMovieDirectorPair(String movie, String director){
        movieRepository.saveMovieDirectorPair(movie, director);
    }
    public Movie findMovieByName(String movie){
       return movieRepository.findMovie(movie);
    }

    public void getMovieByName(String name){
        movieRepository.findMovie(name);
    }
    public Director getDirectorByName(String directorName){
        return movieRepository.findDirector(directorName);
    }
    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }
    public void deleteDirectorByName(String director){
        movieRepository.deleteDirector(director);
    }
    public void deleteAllDirectors(){
        movieRepository.deleteAllDirector();
    }
    public List<String> findMoviesFromDirector(String director){
        return movieRepository.findMoviesFromDirector(director);
    }

    public List<String> getAllDirectorDetails(){
        return movieRepository.getAllDirectorDetails();
    }
    public List<String> getAllMoviesByDirector(){
        return movieRepository.getAllMoviesByDirector();
    }
}
