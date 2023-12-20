package com.korea.MOVIEBOOK.Movie;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequiredArgsConstructor
@Controller
public class movieCotroller {

    private final movieService movieService;

    @GetMapping("movie")
    public String movie(Model model) {

        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonInString = "";
        String key = "f53a4247c0c7eda74780f0c0b855d761";
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        String date = yesterday.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + "?" + "key=f53a4247c0c7eda74780f0c0b855d761&targetDt=" + date).build();

            //이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

            LinkedHashMap lm = (LinkedHashMap) resultMap.getBody().get("boxOfficeResult");
            ArrayList<Map> dboxoffList = (ArrayList<Map>) lm.get("dailyBoxOfficeList");

            LinkedHashMap mnList = new LinkedHashMap<>();

            for (Map obj : dboxoffList) {
                mnList.put(obj.get("rnum"), obj.get("movieNm"));
            }
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(mnList);


            Object movieName = mnList.get("1");
            boolean add = this.movieService.update(1l, (String) movieName, date);   // 조회시 해당 날짜에 대한 데이터 유무확인

            if (add) {
                for (int i = 1; i <= mnList.size(); i++) {
                    Object movieName2 = mnList.get(String.valueOf(i));
                    this.movieService.add((long) i, (String) movieName2, date);
                }
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body", e.getStatusText());
            System.out.println(e.toString());

        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body", "excpetion오류");
            System.out.println(e.toString());
        }
        return "Movie/movie";
    }
}

