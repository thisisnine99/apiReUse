package com.korea.MOVIEBOOK.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/mainPage")
    public String mainPage() {
        return "book/bookMainPage";
    }
}
