package com.korea.MOVIEBOOK.dramaReview;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<Review> getReviewsByDramaId(Long dramaId) {
        return reviewRepository.findByDramaId(dramaId);
    }

    public Review findReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    public void saveReview(Review review) {
        // Review 저장 로직
        reviewRepository.save(review);
    }

    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }

    public Long getDramaIdByReviewId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("리뷰를 찾을 수 없습니다.")); // 해당 ID의 리뷰를 찾습니다.
        return review.getDrama().getId(); // 리뷰가 속한 드라마의 ID를 반환합니다.
    }

    public Page<Review> getReviewsByDramaIdWithPagination(Long dramaId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return reviewRepository.findByDramaId(dramaId, pageable);
    }

    public void updateReview(Review updateReview) {
        Review existingReview = reviewRepository.findById(updateReview.getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id:" + updateReview.getId()));
        existingReview.setTitle(updateReview.getTitle());
        existingReview.setComment(updateReview.getComment());
        existingReview.setRating(updateReview.getRating());
        reviewRepository.save(existingReview);
    }


}




