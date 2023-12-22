package com.korea.MOVIEBOOK.Movie.Date;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDateRepository extends JpaRepository<MovieDate, Long> {
    MovieDate findBydailyDate(String dailyDate);
}
