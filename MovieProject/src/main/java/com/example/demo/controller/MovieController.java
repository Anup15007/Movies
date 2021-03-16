package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.Movie;
import com.example.demo.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	 @Autowired
	    MovieService service;
	 
	    @GetMapping
	    public ResponseEntity<List<Movie>> getAllMovies() {
	        List<Movie> list = service.getAllMovies();
	 
	        return new ResponseEntity<List<Movie>>(list, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @GetMapping("/{movieId}")
	    public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") Long movieId) 
	                                                    throws Exception {
	    	Movie entity = service.getMovieById(movieId);
	 
	        return new ResponseEntity<Movie>(entity, new HttpHeaders(), HttpStatus.OK);
	    }
	 
	    @PostMapping
	    public ResponseEntity<Movie> createOrUpdateMovie(Movie movie)
	                                                    throws Exception {
	    	Movie updated = service.createOrUpdateMovie(movie);
	        return new ResponseEntity<Movie>(updated, new HttpHeaders(), HttpStatus.OK);
	    }
	    @GetMapping("/delete/{id}")
	    public HttpStatus deleteMovieById(@PathVariable("movieId") Long movieId) 
	                                                    throws Exception {
	        service.deleteMovieById(movieId);
	        return HttpStatus.FORBIDDEN;
	    }
	 

}
