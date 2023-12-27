package com.korea.MOVIEBOOK.dramaReview;

import com.korea.MOVIEBOOK.Drama.Drama;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id; // 고유 값
    private String title; // 리뷰 제목
    private String comment; // 리뷰 내용
    private Integer rating; // 리뷰 평점

    @ManyToOne
    @JoinColumn(name = "drama_id")
    private Drama drama;
}
