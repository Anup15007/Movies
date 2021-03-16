package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Movie;
import com.example.demo.dao.MovieDao;
import com.example.demo.exception.MovieDetailsInvaildException;
import com.example.demo.exception.MovieDetailsNotFoundException;
import com.example.demo.exception.MovieIdNotPresentException;

@Service
public class MovieService {
	  @Autowired
	  MovieDao dao;
	     
	    public List<Movie> getAllMovies()
	    {
	        List<Movie> movieList = dao.findAll();
	         
	        if(movieList.size() > 0) {
	            return movieList;
	        } else {
	            return new ArrayList<Movie>();
	        }
	    }
	     
	    public Movie getMovieById(Long movieId) throws MovieDetailsNotFoundException 
	    {
	        Optional<Movie> movie = dao.findById(movieId);
	         
	        if(movie.isPresent()) {
	            return movie.get();
	        } else {
	            try {
					throw new Exception("No employee record exist for given movie id");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			return getMovieById(movieId);
	    }
	     
	    public Movie createOrUpdateMovie(Movie entity) throws MovieDetailsInvaildException
	    {
	        Optional<Movie> movie = dao.findById(entity.getMovieId());
	         
	        if(movie.isPresent()) 
	        {
	        	Movie newEntity = movie.get();
	            newEntity.setMovieName(entity.getMovieName());
	            newEntity = dao.save(newEntity);
	             
	            return newEntity;
	        } else {
	            entity = dao.save(entity);
	             
	            return entity;
	        }
	    } 
	     
	    public void deleteMovieById(Long movieId) throws MovieIdNotPresentException 
	    {
	        Optional<Movie> movie = dao.findById(movieId);
	         
	        if(movie.isPresent()) 
	        {
	            dao.deleteById(movieId);
	        } else {
	            try {
					throw new Exception("No employee record exist for given id");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    } 

}
