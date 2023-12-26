package com.korea.MOVIEBOOK.Movie.Movie;


import com.korea.MOVIEBOOK.Movie.Date.MovieDate;
import com.korea.MOVIEBOOK.Movie.Date.MovieDateService;
import com.korea.MOVIEBOOK.Movie.Movie.Daily.MovieDaily;
import com.korea.MOVIEBOOK.Movie.Movie.Daily.MovieDailyAPI;
import com.korea.MOVIEBOOK.Movie.Movie.Daily.MovieDailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MovieCotroller {

    private final MovieDailyService movieDailyService;
    private final MovieDailyAPI movieDailyAPI;
    private final MovieDateService movieDateService;
    LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
    LocalDateTime today = LocalDateTime.now().minusDays(7);;
    String today2 = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    String date = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    @GetMapping("movie")
    public String movie(Model model) throws ParseException {
        List<MovieDaily> movieDailyList = this.movieDailyService.findDailyMovie(date);  // movieDaily data 확인



        if (movieDailyList.isEmpty()) {
            this.movieDailyAPI.movieDaily(date);
            movieDailySize();
        }

        model.addAttribute("movieDailyDate",date);
        model.addAttribute("movieDailyList",movieDailyList);

        return "Movie/movie";
    }
    public List<MovieDaily> movieDailySize(){
        List<MovieDaily> movieDailyList = this.movieDailyService.findDailyMovie(date);
        if(movieDailyList.isEmpty()) {
            movieDailyAPI.movieDaily(date);
            movieDailyList = this.movieDailyService.findDailyMovie(date);
            if(movieDailyList.size()<10){
                movieDailyDelete();
            }
        }
        return movieDailyList;
    }

    public List<MovieDaily> movieDailyDelete(){
        System.out.println("======재시작=====");
        this.movieDailyService.deleteDailyMovie(date);
        movieDailyAPI.movieDaily(date);
        return movieDailySize();
    }

}

