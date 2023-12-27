package com.korea.MOVIEBOOK.Webtoon.WebtoonList;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Webtoon {


    @Id
    private String _id;

    private Integer fanCount;

    private Long webtoonId;

    private String title;

    private String author;

    private String img;

    @ElementCollection
    private List<String> updateDays;

    private String searchKeyword;

    private String detailUrl;

}
