package com.korea.MOVIEBOOK.dramaReview;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsByDramaId(Long dramaId) {
        return reviewRepository.findByDramaId(dramaId);
    }

    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    public void saveReview(Review review) {
        // Review 저장 로직
        reviewRepository.save(review);
    }

}
