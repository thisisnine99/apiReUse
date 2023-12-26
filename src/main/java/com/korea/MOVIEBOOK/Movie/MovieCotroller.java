package com.korea.MOVIEBOOK.Movie;

import com.korea.MOVIEBOOK.Movie.Daily.MovieDaily;
import com.korea.MOVIEBOOK.Movie.Daily.MovieDailyAPI;
import com.korea.MOVIEBOOK.Movie.Daily.MovieDailyService;
import com.korea.MOVIEBOOK.Movie.Weekly.MovieWeekly;
import com.korea.MOVIEBOOK.Movie.Weekly.MovieWeeklyAPI;
import com.korea.MOVIEBOOK.Movie.Weekly.MovieWeeklyService;
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
    private final MovieWeeklyService movieWeeklyService;
    private final MovieWeeklyAPI movieWeeklyAPI;
    LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
    String date = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    LocalDateTime weeksago = LocalDateTime.now().minusDays(7);
    String weeks = weeksago.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    @GetMapping("movie")
    public String movie(Model model) throws ParseException {
        List<MovieDaily> movieDailyList = this.movieDailyService.findDailyMovie(date);  // movieDaily data 확인
        List<MovieWeekly> movieWeekList = this.movieWeeklyService.findWeeklyMovie(weeks);

        if (movieDailyList.isEmpty()) {
            this.movieDailyAPI.movieDaily(date);
            movieDailySize();
        }
        if (movieWeekList.isEmpty()) {
            this.movieWeeklyAPI.movieWeekly(weeks);
        }
        model.addAttribute("movieDailyDate",date);
        model.addAttribute("movieDailyList",movieDailyList);
        model.addAttribute("movieWeekList",movieWeekList);


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

