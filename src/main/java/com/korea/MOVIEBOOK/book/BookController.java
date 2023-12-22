package com.korea.MOVIEBOOK.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/mainPage")
    public String mainPage() {
        return "book/bookMainPage";
    }

    @GetMapping("/list")
    public String getAPI(Model model) {
        List<BookDTO> bookDTOList = bookService.getBookList();
        model.addAttribute("bookDTOList", bookDTOList);
        System.out.println(bookDTOList.get(0).getTitle());
        System.out.println(bookDTOList.get(0).getAuthor());
        System.out.println(bookDTOList.get(0).getIsbn());
        System.out.println(bookDTOList.get(0).getPubDate());
        System.out.println(bookDTOList.get(0).getPriceStandard());
        return "book/bookList";
    }
}
