package com.korea.MOVIEBOOK.Movie.Movie;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;

@RequiredArgsConstructor
@Controller
public class MovieCotroller {

    private final MovieDailyService movieDailyService;
    private final MovieAPI movieAPI;
    LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
    String date = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    @GetMapping("movie")
    public String movie(Model model) {
        List<MovieDaily> movieDailyList = this.movieDailyService.findDailyMovie(date);
        if(movieDailyList.isEmpty()){
            movieAPI.movieDb(date);
//            if(movieDailyList.size() < 10){
//                JOptionPane.showMessageDialog(null, "오류가 발생하였습니다. 새로고침해주세요!");
//                this.movieDailyService.deleteDailyMovie(date);
//            }
        }  else{
            model.addAttribute("movieDaily",movieDailyList);
        }
        return "Movie/movie";
    }
}

