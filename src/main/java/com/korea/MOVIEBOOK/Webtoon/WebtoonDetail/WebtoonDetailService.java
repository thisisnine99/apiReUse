package com.korea.MOVIEBOOK.Webtoon.WebtoonDetail;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebtoonDetailService {

    private final WebtoonDetailRepository webtoonDetailRepository;

    public Optional<Object> getWebtoonDetailById(Long webtoonId) {

        return Optional.of(webtoonDetailRepository.findById(webtoonId));
    }
}
