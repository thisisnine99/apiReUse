package com.korea.MOVIEBOOK.Drama;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter

public class Drama {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String imageUrl;
    private String jenre;          // 장르
    private LocalDate releaseDate;    // 개봉일
    private String productionCompany;   // 제작사
    private String Director;    // 감독
    private String viewingRating;   // 연령등급



}
