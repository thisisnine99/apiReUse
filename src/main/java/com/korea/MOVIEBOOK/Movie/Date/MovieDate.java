package com.korea.MOVIEBOOK.Movie.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MovieDate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    String dailyDate;    // 날짜 (moviedaily에 data 유무 확인을 위한 칼럼)

}
