package com.korea.MOVIEBOOK.Drama;

import jdk.dynalink.beans.StaticClass;
import lombok.RequiredArgsConstructor;
import nz.net.ultraq.thymeleaf.layoutdialect.models.ElementMerger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.ObjectUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DramaService {

    private final DramaRepository dramaRepository;

    public List<Drama> dramaList() {
        return dramaRepository.findAll();
    }

    public Drama getDramaById(Long id) {
        return dramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 영화는 존재하지 않습니다. " + id));
    }

}



