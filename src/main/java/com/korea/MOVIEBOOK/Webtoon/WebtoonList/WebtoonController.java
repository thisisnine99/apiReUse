package com.korea.MOVIEBOOK.Webtoon.WebtoonList;

import com.korea.MOVIEBOOK.Webtoon.Day;
import com.korea.MOVIEBOOK.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.repository.util.ClassUtils.ifPresent;

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
            System.out.println(webtoonDTOList.get(0).get_id());
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


    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> addWebtoon(@RequestBody WebtoonDTO webtoonDTO) {
        webtoonService.saveWebtoonFromDTO(webtoonDTO);
        return ResponseEntity.ok("Webtoon added successfully");
    }


    @GetMapping("/detail/{webtoonId}")
    public String WebtoonDetail(Model model, @PathVariable Long webtoonId) {
        Optional<Webtoon> webtoonDTO = webtoonService.createSampleWebtoonDetail(webtoonId);
        model.addAttribute("webtoonDTO", webtoonDTO);
        return "webtoon/webtoon_detail";
    }
}


