package com.korea.MOVIEBOOK.Movie.Daily;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class MovieDaily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String date;                // 조회 일자        - LocalDate

    Long rank;                  // 순위             - 영화 진흥원 API

    String title;               // 영화 제목        - 영화 진흥원 API

    String director;            // 영화 감독        - 영화 진흥원 API

    @Column(columnDefinition = "text")
    String actor;               // 배우명 (역할)    - 영화 진흥원 API

    String runtime;             // 상영 시간        - 영화 진흥원 API

    @Column(columnDefinition = "text")
    String plot;                // 줄거리           - KMDb API

    String genre;               // 장르             - 영화 진흥원 API

    String releaseDate;         // 개봉 일자        - 영화 진흥원 API

    String company;             // 제작사           - KMDb API

    String nations;             // 제작 국가        - 영화 진흥원 API

    Long audiAcc;               // 누적 관객수      - 영화 진흥원 API

    String viewingRating;       // 관람 등급        - 영화 진흥원 API

    String imageUrl;            // 포스터 URL       - KMDb API

}
