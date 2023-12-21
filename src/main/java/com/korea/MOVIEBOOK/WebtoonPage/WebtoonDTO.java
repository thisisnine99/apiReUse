package com.korea.MOVIEBOOK.WebtoonPage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WebtoonDTO {

//    public static class WebtoonData {
            private String id;
        private String title;
        private String author;
        private String img;
        private List<String> updateDays;
        private String searchKeyword;
//    }
}
