package com.korea.MOVIEBOOK.dramaReview;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByDramaId(Long dramaId);

}
