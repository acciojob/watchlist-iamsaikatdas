package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    private Map<String, Movie> movieMap;
    private Map<String, Director> directorMap;
    private Map<String, List<String>> directorMovieMapping;

    // constructor
    public MovieRepository() {
        this.movieMap = new HashMap<String, Movie>();
        this.directorMap = new HashMap<String, Director>();
        this.directorMovieMapping = new HashMap<String, List<String>>();
    }

    //save movie
    public void saveMovie(Movie movie){
        movieMap.put(movie.getName(), movie);
    }
    public void saveDirector(Director director){
        directorMap.put(director.getName(), director);
    }
    public void saveMovieDirectorPair(String movie, String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            List<String> currentMoviesByDirector = new ArrayList<>();

            if(directorMovieMapping.containsKey(director)){
                currentMoviesByDirector = directorMovieMapping.get(director);
            }
            currentMoviesByDirector.add(movie);

            directorMovieMapping.put(director, currentMoviesByDirector);
        }
    }

    public Movie findMovie(String movie){
        return movieMap.get(movie);
    }
    public Director findDirector(String director){
        return directorMap.get(director);
    }
    // get all movies
    public List<String> findAllMovies(){
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director){
        List<String> allMovies = new ArrayList<String >();
        // 1. find all movies and save allmovies list
        if (directorMovieMapping.containsKey(director)){
            allMovies = directorMovieMapping.get(director);
        }
        //2. delete the all movie from movieMap db;
        for (String movie:allMovies){
            movieMap.remove(movie);
        }
        // 3. also deleting the director pair
        directorMovieMapping.remove(director);
        //4. delete the director from directMap
        if (directorMap.containsKey(director)){
            directorMap.remove(director);
        }
    }

    public void deleteAllDirector(){
        HashSet<String> movieSet = new HashSet<String>();

        directorMap = new HashMap<>();

        for (String director:directorMovieMapping.keySet()){

            for (String movie:directorMovieMapping.get(director)){
                movieSet.add(movie);
            }
        }
        for(String movie:movieSet){
            if (movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        directorMovieMapping = new HashMap<>();
    }

    public List<String> findMoviesFromDirector(String director){
        List<String> moviesList = new ArrayList<String>();
        if(directorMovieMapping.containsKey(director))
            moviesList = directorMovieMapping.get(director);

        return moviesList;
    }

    public List<String> getAllDirectorDetails(){
        return new ArrayList<>(directorMap.keySet());
    }

    public List<String> getAllMoviesByDirector(){
        return new ArrayList<>(directorMovieMapping.keySet());
    }
}
