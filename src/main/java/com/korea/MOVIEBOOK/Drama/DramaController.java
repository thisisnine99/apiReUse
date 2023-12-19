package com.korea.MOVIEBOOK.Drama;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DramaController {

    private final DramaService dramaService;

    @GetMapping("/drama/dramaList")
    public String dramaList (Model model) {
        List<Drama> dramaList = dramaService.dramaList();
        model.addAttribute("dramaList", dramaList);
        return "drama/drama_list";
    }

    @GetMapping("/drama/{id}")
    public String dramaDetail(@PathVariable Long id, Model model) {
        Drama drama = dramaService.getDramaById(id);
        model.addAttribute("drama", drama);
        return "drama/drama_detail";
    }

}



