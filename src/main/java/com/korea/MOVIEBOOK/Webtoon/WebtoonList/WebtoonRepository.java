package com.korea.MOVIEBOOK.Webtoon.WebtoonList;

import com.korea.MOVIEBOOK.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

    List<Webtoon> findByUpdateDays(String day);
}
