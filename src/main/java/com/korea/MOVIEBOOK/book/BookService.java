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
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    private void getAPI(String command) {
        //  기본 url에 덧붙일 url 명령어를 넣으면 api를 사용해서 이미 db에있는지 체크하고 없다면 db에 저장해주는 메서드.
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            String url = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?ttbkey=ttbdlrjsrn81027001";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + command).build();

            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

            ArrayList<Map<String, Object>> bookList = (ArrayList<Map<String, Object>>) resultMap.getBody().get("item");
            for (Map<String, Object> bookData : bookList) {
                if (checkDuplicate((String) bookData.get("isbn"))) {
                    saveBook(bookData);
                    System.out.println("============================= 책 추가됨 =============================");
                }
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // 예외 처리
            e.printStackTrace();
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
        }
    }
    public void addBestSeller() {
        String command = "&QueryType=Bestseller&MaxResults=30&start=1&Cover=Big&SearchTarget=Book&output=JS&Version=20131101";
        getAPI(command);
    }

    public List<Book> getBestSellerList() {
        //  db에 있는 책들중 bestSeller들만 추려서 List를 리턴하는 함수
        List<Book> bookList = bookRepository.findAll();
        List<Book> bestSellerList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getBestRank() != null) {
                bestSellerList.add(book);
            }
        }
        return bestSellerList;
    }

    private Boolean checkDuplicate(String isbn) {
        //  이미 DB에 저장되어있는 책인지 확인하는 함수
        List<Book> bookList = bookRepository.findAll();
        boolean answer = true;
        for (Book book : bookList) {
            if (book.getIsbn().equals(isbn)) {
                answer = false;
                break;
            }
        }
        return answer;
    }
    private void saveBook(Map<String, Object> bookData) {
        //  book객체를 db에 저장하는 함수
        Book book = new Book();
        book.setTitle((String) bookData.get("title"));
        book.setAuthor((String) bookData.get("author"));
        book.setDescription((String) bookData.get("description"));
        book.setIsbn((String) bookData.get("isbn"));
        book.setIsbn13((String) bookData.get("isbn13"));
        book.setCover((String) bookData.get("cover"));
        book.setPublisher((String) bookData.get("publisher"));
        book.setPricestandard((Integer) bookData.get("priceStandard"));
        book.setBestRank((Integer) bookData.get("bestRank"));
        book.setPubdate(getLocalDate(bookData.get("pubDate")));
        bookRepository.save(book);
    }
    private BookDTO createBookDTO(Map<String, Object> bookData) {
        try {
            return BookDTO.builder()
                    .title((String) bookData.get("title"))
                    .author((String) bookData.get("author"))
                    .description((String) bookData.get("description"))
                    .isbn((String) bookData.get("isbn"))
                    .isbn13((String) bookData.get("isbn13"))
                    .cover((String) bookData.get("cover"))
                    .publisher((String) bookData.get("publisher"))
                    .priceStandard((Integer) bookData.get("priceStandard"))
                    .bestRank((Integer) bookData.get("bestRank"))
                    .pubDate(getLocalDate(bookData.get("pubDate")))
                    .build();
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            return null;
        }
    }

    private LocalDate getLocalDate(Object dateObj) {
        //  String으로 받은 날짜 데이터를 LocalDate타입으로 변경해주는 함수
        String dateString = (String) dateObj;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate pubDate = LocalDate.parse(dateString, formatter);
        return pubDate;
    }
}
