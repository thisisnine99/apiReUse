package com.korea.MOVIEBOOK.Webtoon.WebtoonDetail;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/webtoon")
public class WebtoonDetailController {

    private final WebtoonDetailService webtoonDetailService;


   @GetMapping("/detail/{webtoonId}")
    public String WebtoonDetail(Model model, @PathVariable Long webtoonId){
       webtoonDetailService.getWebtoonDetailById(webtoonId)
               .ifPresent(webtoonDetail -> model.addAttribute("webtoonDetail", webtoonDetail));
       return "webtoon/webtoon_detail";
    }
}
