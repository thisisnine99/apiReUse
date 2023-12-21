//package com.korea.MOVIEBOOK.WebtoonPage;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//
//
//import java.util.*;
//
//@RequiredArgsConstructor
//@Controller
//public class WebtoonApi {
//
////    private final WebtoonService webtoonService;
//
//    @GetMapping("/webtoon")
//    public String getAPI() {
//        HashMap<String, Object> result = new HashMap<String, Object>();
//        String jsonInString = "";
//
//        try {
//            RestTemplate restTemplate = new RestTemplate();
//            HttpHeaders header = new HttpHeaders();
//            HttpEntity<?> entity = new HttpEntity<>(header);
//            String url = "https://korea-webtoon-api.herokuapp.com";
//            String com = "kakao";
//
//
//            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url+ "/?service="+ com +"&updateDay=mon").build();
//
//            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);
//            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
//            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
//            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
//
//
////            LinkedHashMap lm = (LinkedHashMap) resultMap.getBody().get("totalWebtoonCount");
//            ArrayList<Map> webtoonsList = (ArrayList<Map>) resultMap.getBody().get("webtoons");
//            LinkedHashMap mnList = new LinkedHashMap<>();
//            for (Map obj : webtoonsList) {
//                System.out.println("Title: " + obj.get("title") + ", Img: " + obj.get("img") + ", Author: " + obj.get("author"));
////                webtoonService.save((String)obj.get("title"), (String)obj.get("img"), (String)obj.get("author"));
//            }
//
//            ObjectMapper mapper = new ObjectMapper();
//            jsonInString = mapper.writeValueAsString(mnList);
//
//        }
//        catch (HttpClientErrorException | HttpServerErrorException e) {
//            result.put("statusCode", e.getRawStatusCode());
//            result.put("body", e.getStatusText());
//            System.out.println(e.toString());
//
//        }
//        catch (Exception e) {
//            result.put("statusCode", "999");
//            result.put("body", "excpetion오류");
//            System.out.println(e.toString());
//        }
//        return "error";
//    }
//}