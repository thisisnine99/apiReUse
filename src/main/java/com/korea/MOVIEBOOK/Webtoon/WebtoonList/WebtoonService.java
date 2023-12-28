package com.korea.MOVIEBOOK.Webtoon.WebtoonList;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;

    public List<WebtoonDTO> getWebtoonList(String day) {
        List<WebtoonDTO> webtoonDTOList = new ArrayList<>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
            String url = "https://korea-webtoon-api.herokuapp.com";
            String com = "naver";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url + "/?" + "service=naver&updateDay=mon").build();


            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class);

            ArrayList<Map> webtoonsList = (ArrayList<Map>) resultMap.getBody().get("webtoons");
            for (Map<String, Object> webtoonData : webtoonsList) {
                // BookDTO 생성 코드
                WebtoonDTO webtoonDTO = createWebtoonDTOFromMap(webtoonData);
                webtoonDTOList.add(webtoonDTO);
                saveWebtoonFromDTO(webtoonDTO);
            }

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println(e.toString());
            // 예외 처리 로직 추가
        } catch (Exception e) {
            System.out.println(e.toString());
            // 예외 처리 로직 추가
        }

        return webtoonDTOList;
    }

    private WebtoonDTO createWebtoonDTOFromMap(Map<String, Object> webtoonData) {
        try {
            return WebtoonDTO.builder()
                    ._id((String) webtoonData.get("_id"))
                    .fanCount((Integer) webtoonData.get("fanCount"))
                    .webtoonId((Long) webtoonData.get("webtoonId"))
                    .title((String) webtoonData.get("title"))
                    .author((String) webtoonData.get("author"))
                    .img((String) webtoonData.get("img"))
                    .updateDays((List<String>) webtoonData.get("updateDays"))
                    .searchKeyword((String)webtoonData.get("searchKeyword"))
                    .detailUrl((String)webtoonData.get("detailUrl"))
                    .build();
        } catch (Exception e) {
            // 예외 처리
            e.printStackTrace();
            System.out.println("id================>" + (String)webtoonData.get("_id"));
            return null;
        }
    }

    public void saveWebtoonFromDTO(WebtoonDTO webtoonDTO) {
        Webtoon webtoon = new Webtoon();
        webtoon.set_id(webtoonDTO.get_id());
        webtoon.setFanCount(webtoonDTO.getFanCount());
        webtoon.setWebtoonId(webtoonDTO.getWebtoonId());
        webtoon.setTitle(webtoonDTO.getTitle());
        webtoon.setAuthor(webtoonDTO.getAuthor());
        webtoon.setImg(webtoonDTO.getImg());
        webtoon.setUpdateDays(webtoonDTO.getUpdateDays());
        webtoon.setDetailUrl(webtoonDTO.getDetailUrl());
        webtoonRepository.save(webtoon);
    }


//    public Optional<Object> getWebtoonDetailById(Long webtoonId) {
//        return Optional.of(webtoonRepository.findById(webtoonId));
//    }

    public Optional<Webtoon> createSampleWebtoonDetail(Long webtoonId) {
        return webtoonRepository.findById(webtoonId);
    }
}


