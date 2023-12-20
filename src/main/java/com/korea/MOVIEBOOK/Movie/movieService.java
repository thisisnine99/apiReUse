package com.korea.MOVIEBOOK.Movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
public class movieService {
    private final movieRepository movieRepository;

    public void add(Long rank, String title, String date){
        movie movie = new movie();
        movie.setRank(rank);
        movie.setTitle(title);
        movie.setDate(date);
        this.movieRepository.save(movie);
    }

    public boolean update(Long rank, String title,String date){
        List<movie> movies = this.movieRepository.findBydate(date);
        if(movies.isEmpty()){
            return true;
        } return false;
    }
}
