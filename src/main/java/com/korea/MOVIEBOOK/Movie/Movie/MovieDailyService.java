package com.korea.MOVIEBOOK.Movie.Movie;

import com.korea.MOVIEBOOK.Movie.Date.MovieDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class MovieDailyService {
    private final MovieDailyRepository movieDailyRepository;
    private final MovieDateService movieDateService;

    public void addKmdb(String plot, String company, String imageUrl, String director) {
            MovieDaily movie = this.movieDailyRepository.findBydirector(director);
            String plotcontent = plot.replaceAll("!HS", "").replaceAll("!HE", "").replaceAll("\\s+", "");
            movie.setPlot(plotcontent);
            movie.setCompany(company);
            movie.setImageUrl(imageUrl);
            this.movieDailyRepository.save(movie);
    }

    public void addDeail(String date, String actorText, String runtime, String genre, String releaseDate, String viewingRating, String director) {

        if (this.movieDateService.add(date)) {
            MovieDaily movie = new MovieDaily();
            movie.setActor(actorText);
            movie.setRuntime(runtime);
            movie.setGenre(genre);
            movie.setReleaseDate(releaseDate);
            movie.setViewingRating(viewingRating);
            movie.setDirector(director);
            this.movieDailyRepository.save(movie);
        }
    }

    public boolean add(Long rank, String title, Long audiAcc, String date, String director) {
        MovieDaily movie = this.movieDailyRepository.findBydirector(director);
        if (this.movieDateService.add(date)) {
            movie.setRank(rank);
            movie.setTitle(title);
            movie.setAudiAcc(audiAcc);
            movie.setDate(date);
            this.movieDailyRepository.save(movie);
            return true;
        }
        return false;
    }

    public boolean update(Long rank, String title, String date) {
        List<MovieDaily> movies = this.movieDailyRepository.findBydate(date);
        if (movies.isEmpty()) {
            return true;
        }
        return false;
    }

    public List<MovieDaily> findDailyMovie(String date){
        return this.movieDailyRepository.findBydate(date);
    }

    public void deleteDailyMovie(String date){
        List<MovieDaily> movieDailyList = this.movieDailyRepository.findBydate(date);
        int i = 0 ;
        while( i < movieDailyList.size() ){
            this.movieDailyRepository.delete(movieDailyList.get(i));
        }
    }

}
