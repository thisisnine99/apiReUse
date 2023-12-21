package com.korea.MOVIEBOOK.Drama;

import com.korea.MOVIEBOOK.dramaReview.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DramaService {

    private final DramaRepository dramaRepository;

    public List<Drama> dramaList() {
        return dramaRepository.findAll();
    }

    public Drama getDramaById(Long id) {
        return dramaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 드라마는 존재하지 않습니다. " + id));
    }

    public List<Review> getReviewsByDramaId(Long dramaId) {
        Drama drama = dramaRepository.findById(dramaId)
                .orElseThrow(() -> new RuntimeException("해당 드라마는 존재하지 않습니다. " + dramaId));
        return drama.getReviews();
    }


}



