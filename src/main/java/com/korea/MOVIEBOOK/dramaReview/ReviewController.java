package com.korea.MOVIEBOOK.dramaReview;

import com.korea.MOVIEBOOK.Drama.Drama;
import com.korea.MOVIEBOOK.Drama.DramaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final DramaService dramaService;

    @PostMapping("/reviews/drama/{dramaId}")
    public String addReview(@PathVariable Long dramaId, @ModelAttribute("newReview") Review newReview) {
        Drama drama = dramaService.getDramaById(dramaId);
        Review review = new Review();
        review.setComment(newReview.getComment());
        review.setDrama(drama);
        review.setRating(newReview.getRating());
        reviewService.saveReview(review);
        return "redirect:/drama/" + dramaId;
    }
}
