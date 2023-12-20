package com.korea.MOVIEBOOK.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BookDTO {
    private String title;   //  제목
    private String author;  //  작가
    private String description; //  요약
    private String isbn;    //  10자리 코드
    private String isbn13;  //  13자리 코드(가급적 13자리 코드사용)
    private LocalDateTime pubdate; //  출간일
    private int pricestandard;   //  정가
    private String cover;   //  표지
    private String publisher;   //  출판사
}
