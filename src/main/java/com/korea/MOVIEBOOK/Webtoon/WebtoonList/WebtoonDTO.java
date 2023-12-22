package com.korea.MOVIEBOOK.Webtoon.WebtoonList;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WebtoonDTO {

    private List<List<String>> listOfLists;

    //    public static class WebtoonData {
    private String id;
    private Long webtoonId;
    private String title;
    private String author;
    private String img;
    private List<String> updateDays;
    private String searchKeyword;
    private String detailUrl;
//    }
}
