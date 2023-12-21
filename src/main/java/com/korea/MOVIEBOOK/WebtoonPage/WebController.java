package com.korea.MOVIEBOOK.WebtoonPage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/webtoon")
public class WebController {

    private final WebtoonService webtoonService;

    @GetMapping("/list")
    public String webtoonList(Model model){
        List<Webtoon> webtoonList = webtoonService.findAllWebtoonList();
        model.addAttribute("webtoonList", webtoonList);
        return "webtoon/webtoon_list";
    }


}
