package com.korea.MOVIEBOOK.Movie;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long rank;  // 순위

    String title;   // 영화제목

    String date; // 조회일자
}
