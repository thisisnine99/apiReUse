package com.korea.MOVIEBOOK.Webtoon.WebtoonList;

import com.korea.MOVIEBOOK.Webtoon.Day;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/webtoon")
public class WebtoonController {

    private final WebtoonService webtoonService;

//    @GetMapping("/list")
//    public String WebtoonList(Model model){
//        Day[] values = Day.values();
//        List<List<Webtoon>> webtoonListList = new ArrayList<>();
//
//        for (Day value : values) {
//            // value.name().toLowerCase()를 통해 요일 문자열을 얻어온다.
//            String dayString = value.name().toLowerCase();
//
//            // value.name().toLowerCase()에 해당하는 요일의 웹툰 리스트를 가져온다.
//            List<Webtoon> mondatList = webtoonService.getWebtoonList("mon");
//            model.addAttribute("mondatList", mondatList);
//
//            // dayWebtoonList가 비어있지 않을 경우에만 처리
//            if (!mondatList.isEmpty()) {
//                // dayWebtoonList를 정렬한다. (예: fanCount에 기반한 정렬)
//                mondatList.sort(Comparator.comparing(Webtoon::getFanCount));
//
//                // 5개씩 잘라서 리스트에 추가
//                int startIndex = 0;
//                int endIndex = 5;
//                for (int i = 0; i <= mondatList.size() / 5; i++) {
//                    webtoonListList.add(mondatList.subList(startIndex, Math.min(endIndex, mondatList.size())));
//                    startIndex += 5;
//                    endIndex += 5;
//                }
//            }
//        }
//        model.addAttribute("webtoonListList", webtoonListList);
//        return "webtoon/webtoon_list";
//    }



    @GetMapping("/list")
    public String getApi(Model model) {

        Day[] values = Day.values();

        for (Day value : values) {
            List<Webtoon> mondayList = webtoonService.getWebtoonList("mon");
            model.addAttribute("mondayList", mondayList);
        }
        for (Day value : values) {
            List<Webtoon> tuesdayList  = webtoonService.getWebtoonList("tue");
            model.addAttribute("tuesdayList", tuesdayList);
        }for (Day value : values) {
            List<Webtoon> wednesdayList = webtoonService.getWebtoonList("wed");
            model.addAttribute("wednesdayList", wednesdayList);
        }for (Day value : values) {
            List<Webtoon> thursdayList  = webtoonService.getWebtoonList("thu");
            model.addAttribute("thursdayList", thursdayList);
        }for (Day value : values) {
            List<Webtoon> fridayList = webtoonService.getWebtoonList("fri");
            model.addAttribute("fridayList", fridayList);
        }for (Day value : values) {
            List<Webtoon> saturdayList = webtoonService.getWebtoonList("sat");
            model.addAttribute("saturdayList", saturdayList);
        }for (Day value : values) {
            List<Webtoon> sundayList = webtoonService.getWebtoonList("sun");
            model.addAttribute("sundayList", sundayList);
        }
        return "webtoon/webtoon_list";
    }

    @GetMapping("/detail/{webtoonId}")
    public String WebtoonDetail(Model model, @PathVariable Long webtoonId) {
        Optional<Webtoon> webtoonDTO = webtoonService.createSampleWebtoonDetail(webtoonId);
        model.addAttribute("webtoonDTO", webtoonDTO);
        return "webtoon/webtoon_detail";
    }
}


