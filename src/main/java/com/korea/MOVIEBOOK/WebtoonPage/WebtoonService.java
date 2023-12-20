package com.korea.MOVIEBOOK.WebtoonPage;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    public List<Webtoon> findAllWebtoonList() {
        return webtoonRepository.findAll();
    }
}
