package com.korea.MOVIEBOOK.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
public List<BookDTO> getBestSellerList() {
    List<BookDTO> bookDTOList = new ArrayList<>();
    try {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(header);
        String url = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlrjsrn81027001&QueryType=Bestseller&MaxResults=30&start=1&SearchTarget=Book&output=JS&Version=20131101";
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

        ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
        // 여기서 resultMap으로 부터 받은 데이터를 가공

        ArrayList<Map<String, Object>> bookList = (ArrayList<Map<String, Object>>) resultMap.getBody().get("item");

        for (Map<String, Object> bookData : bookList) {
            // BookDTO 생성 코드
            BookDTO bookDTO = createBookDTOFromMap(bookData);
            bookDTOList.add(bookDTO);
        }
    } catch (HttpClientErrorException | HttpServerErrorException e) {
        // 예외 처리
        e.printStackTrace();
    } catch (Exception e) {
        // 예외 처리
        e.printStackTrace();
    }
    return bookDTOList;
}

    private BookDTO createBookDTOFromMap(Map<String, Object> bookData) {
        try {
            return BookDTO.builder()
                    .title((String) bookData.get("title"))
                    .author((String) bookData.get("author"))
                    .description((String) bookData.get("description"))
                    .isbn((String) bookData.get("isbn"))
                    .isbn13((String) bookData.get("isbn13"))
                    .pubDate(getLocalDate(bookData.get("pubDate")))
                    .priceStandard(getIntegerValue(bookData.get("priceStandard")))
                    .cover((String) bookData.get("cover"))
                    .publisher((String) bookData.get("publisher"))
                    .build();
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    private LocalDate getLocalDate(Object dateObj) {
        // Integer로 변환하여 반환하는 메서드
        // 예외 처리 필요
        String dateString = (String) dateObj;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate pubDate = LocalDate.parse(dateString, formatter);
        return pubDate;
    }

    private int getIntegerValue(Object value) {
        // Integer로 변환하여 반환하는 메서드
        // 예외 처리 필요
        int pricestandard = 0;
        if (value instanceof Integer) {
            pricestandard = (int) value;
        }
        return pricestandard;
    }

}
