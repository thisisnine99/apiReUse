package com.korea.MOVIEBOOK.Webtoon.WebtoonList;

import com.korea.MOVIEBOOK.Webtoon.Day;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/webtoon")
public class WebtoonController {

    private final WebtoonService webtoonService;

    @GetMapping("/list")
    public String getApi(Model model) {

        Day[] values = Day.values();

        for (Day value : values) {
            List<WebtoonDTO> webtoonDTOList = webtoonService.getWebtoonList(String.valueOf(value));
            model.addAttribute("webtoonDTOList", webtoonDTOList);
            System.out.println(webtoonDTOList.get(0).getId());
            System.out.println(webtoonDTOList.get(0).getWebtoonId());
            System.out.println(webtoonDTOList.get(0).getTitle());
            System.out.println(webtoonDTOList.get(0).getAuthor());
            System.out.println(webtoonDTOList.get(0).getImg());
            System.out.println(webtoonDTOList.get(0).getUpdateDays());
            System.out.println(webtoonDTOList.get(0).getSearchKeyword());
            System.out.println(webtoonDTOList.get(0).getDetailUrl());
        }


        return "webtoon/webtoon_list";
    }


}
