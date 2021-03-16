package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.Movie;
@Repository
public interface MovieDao extends JpaRepository<Movie, Long> {

}
