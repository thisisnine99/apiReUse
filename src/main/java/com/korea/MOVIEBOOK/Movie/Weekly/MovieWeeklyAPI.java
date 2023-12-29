package com.korea.MOVIEBOOK.Movie.Weekly;

import com.korea.MOVIEBOOK.Movie.MovieAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
public class MovieWeeklyAPI {
    private final MovieAPI movieAPI;
    private final MovieWeeklyService movieWeeklyService;
    public void movieWeekly(String date) {

        HashMap<String, Object> result = new HashMap<String, Object>();
        String key = "f53a4247c0c7eda74780f0c0b855d761";
        Map rData = null;

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            String url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + "?" + "key=" + key + "&targetDt=" + date + "&weekGb=0").build();

//            ResponseEntity<String> df = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);

            //이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

            LinkedHashMap wm = (LinkedHashMap) resultMap.getBody().get("boxOfficeResult");
            ArrayList<Map> dboxoffList = (ArrayList<Map>) wm.get("weeklyBoxOfficeList");

            if (dboxoffList.size() < 10) {
                movieWeekly(date);
            }
            int j = 0;
            for (Map map : dboxoffList) {
//                String releaseDts = this.movieAPI.movieDetail((String) map.get("movieCd"), date, 1);
//                this.movieAPI.kmdb((String) map.get("movieNm"), releaseDts, 1);
//                rData = this.movieAPI.movieDetail((String) map.get("movieCd"), date, 1);  // api2 호출
                this.movieAPI.kmdb((String) map.get("movieNm"), (String)rData.get("releaseDateAndNationNm"), 1);
                this.movieWeeklyService.add(date, Long.parseLong((String) map.get("rank")), (String) map.get("movieNm"), Long.parseLong((String) map.get("audiAcc")));
                j++;
                System.out.println("=======j의값====" + j);
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body", e.getStatusText());
            System.out.println(e.toString());

        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body", "excpetion오류");
            System.out.println(e.toString());
//            List<String> failedMovieList = (List<String>)rData.get("failedMovieList");
//            System.out.println(failedMovieList);
        }
    }
}
