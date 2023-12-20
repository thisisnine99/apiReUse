package com.korea.MOVIEBOOK.Movie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface movieRepository extends JpaRepository<movie,Long> {
    List<movie> findBydate(String date);
}
