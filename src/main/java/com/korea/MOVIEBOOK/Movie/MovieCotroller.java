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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
            movieWeeklySize();
        }

        LocalDateTime localDateTime = weeksago;
        Date weekdate = Date.from(localDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
        String week = getCurrentWeekOfMonth(weekdate);

        model.addAttribute("movieDailyDate",date);
        model.addAttribute("movieDailyList",movieDailyList);
        model.addAttribute("movieWeeklyDate",week);
        model.addAttribute("movieWeekList",movieWeekList);


        return "Movie/movie";
    }

    @PostMapping("movie/detail")
    public String movieDetail(Model model, @RequestParam String date, @RequestParam String title){
        MovieDaily movieDaily = this.movieDailyService.findmovie(date,title);
        model.addAttribute("movieDailyDetail",movieDaily);

        return "Movie/movie_detail";
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

    public List<MovieWeekly> movieWeeklySize() throws ParseException {
        List<MovieWeekly> movieWeeklyList = this.movieWeeklyService.findWeeklyMovie(weeks);
        if(movieWeeklyList.isEmpty()) {
            this.movieWeeklyAPI.movieWeekly(weeks);
            movieWeeklyList = this.movieWeeklyService.findWeeklyMovie(weeks);
            if(movieWeeklyList.size()<10){
                movieWeeklyDelete();
            }
        }
        return movieWeeklyList;
    }

    public List<MovieWeekly> movieWeeklyDelete() throws ParseException {
        System.out.println("======재시작=====");
        this.movieWeeklyService.deleteWeeklyMovie(weeks);
        this.movieWeeklyAPI.movieWeekly(weeks);
        return movieWeeklySize();
    }

    public static String getCurrentWeekOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1; // calendar에서의 월은 0부터 시작
        int day = calendar.get(Calendar.DATE);

        // 한 주의 시작은 월요일이고, 첫 주에 4일이 포함되어있어야 첫 주 취급 (목/금/토/일)
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setMinimalDaysInFirstWeek(4);

        int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

        // 첫 주에 해당하지 않는 주의 경우 전 달 마지막 주차로 계산
        if (weekOfMonth == 0) {
            calendar.add(Calendar.DATE, -day); // 전 달의 마지막 날 기준
            return getCurrentWeekOfMonth(calendar.getTime());
        }

        // 마지막 주차의 경우
        if (weekOfMonth == calendar.getActualMaximum(Calendar.WEEK_OF_MONTH)) {
            calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); // 이번 달의 마지막 날
            int lastDaysDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 이번 달 마지막 날의 요일

            // 마지막 날이 월~수 사이이면 다음달 1주차로 계산
            if (lastDaysDayOfWeek >= Calendar.MONDAY && lastDaysDayOfWeek <= Calendar.WEDNESDAY) {
                calendar.add(Calendar.DATE, 1); // 마지막 날 + 1일 => 다음달 1일
                return getCurrentWeekOfMonth(calendar.getTime());
            }
        }

        return month + "월 " + weekOfMonth + "주차";
    }

}

