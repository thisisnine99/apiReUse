package com.korea.MOVIEBOOK.Webtoon.WebtoonDetail;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class WebtoonDetail {

    @Id
    @Column(name = "webtoon_id")
    private String webtoonId;

    private String title; // 제목

    private String content; // 줄거리

    private String author;

    private String vote; // 평점

    private String answer; // 댓글

    private String imageUrl; // 메인 표지

    private String imageUrl2; // 서브 표지
}
