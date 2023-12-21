package com.korea.MOVIEBOOK.WebtoonPage;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Webtoon {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(length = 30)
    private String title;

    private String imageUrl;

    private String author;

    private String updateDay;

}
