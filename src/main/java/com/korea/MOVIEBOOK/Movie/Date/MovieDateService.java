package com.korea.MOVIEBOOK.Movie.Date;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MovieDateService {
    private final MovieDateRepository movieDateRepository;

    public void add(Integer year, Integer week){
        MovieDate movieDate = new MovieDate();
        movieDate.setYear(year);
        movieDate.setWeek(week);
        this.movieDateRepository.save(movieDate);
    }
}
