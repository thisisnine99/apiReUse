package com.korea.MOVIEBOOK.Movie.Weekly;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieWeeklyService {
    private final MovieWeeklyRepository movieWeeklyRepository;

    String dateString = "";

    public String weeklydate(String date) throws ParseException {

        dateString = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dateparse = sdf.parse(dateString);

        // Calendar 객체를 사용하여 주차 계산
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateparse);

        String weekNumber = String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR));

        return weekNumber;
    }

    public void addKmdb(String date, String plot, String company, String imageUrl, String title) throws ParseException {

        String week = weeklydate(String.valueOf(Integer.parseInt(date) - 7));
        String year = week.substring(0,4);
        MovieWeekly movieWeekly = this.movieWeeklyRepository.findByYearAndWeekAndTitle(year,week,title);
        String plotcontent = plot.replaceAll("!HS", "").replaceAll("!HE", "").replaceAll("\\s+", "");
        movieWeekly.setPlot(plotcontent);
        movieWeekly.setCompany(company);
        movieWeekly.setImageUrl(imageUrl);
        this.movieWeeklyRepository.save(movieWeekly);

    }
    public void addDeail(String date, String movieNm, String actorText, String runtime, String genre, String releaseDate, String viewingRating, String director, String nations) throws ParseException {
        String week = weeklydate(date);
        MovieWeekly movieWeekly = new MovieWeekly();
        movieWeekly.setYear(date.substring(0,4));
        movieWeekly.setWeek(week);
        movieWeekly.setActor(actorText);
        movieWeekly.setRuntime(runtime);
        movieWeekly.setGenre(genre);
        movieWeekly.setReleaseDate(releaseDate);
        movieWeekly.setViewingRating(viewingRating);
        movieWeekly.setDirector(director);
        movieWeekly.setTitle(movieNm);
        movieWeekly.setNations(nations);
        this.movieWeeklyRepository.save(movieWeekly);
    }

    public void add(String date, Long rank, String title, Long audiAcc) throws ParseException {
        String week = weeklydate(date);
        String year = week.substring(0,4);
        MovieWeekly movieWeekly = this.movieWeeklyRepository.findByYearAndWeekAndTitle(year,week,title);
        movieWeekly.setRank(rank);
        movieWeekly.setAudiAcc(audiAcc);
        this.movieWeeklyRepository.save(movieWeekly);
    }

    public List<MovieWeekly> findWeeklyMovie(String weeks) throws ParseException {
        String week = weeklydate(weeks);
        String year = weeks.substring(0,4);
        return this.movieWeeklyRepository.findByYearAndWeek(year,week);
    }
}
