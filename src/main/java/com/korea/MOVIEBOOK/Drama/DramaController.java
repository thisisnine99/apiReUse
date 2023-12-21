package com.korea.MOVIEBOOK.Drama;
import com.korea.MOVIEBOOK.dramaReview.Review;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DramaController {

    private final DramaService dramaService;


    @GetMapping("/drama/dramaList")
    public String dramaList (Model model) {
        List<Drama> dramaList = dramaService.dramaList();
        model.addAttribute("dramaList", dramaList);
        return "drama/drama_list";
    }

    @GetMapping("/drama/{id}")
    public String dramaDetail(@PathVariable Long id, Model model) {
        Drama drama = dramaService.getDramaById(id);
        List<Review> reviews = dramaService.getReviewsByDramaId(id);
        double avgRating = reviews.stream()
                        .mapToInt(Review::getRating)
                                .average()
                                        .orElse(0.0);
        model.addAttribute("drama", drama);
        model.addAttribute("reviews", reviews);
        model.addAttribute("newReview", new Review());
        model.addAttribute("avgRating", String.format("%.1f", avgRating));  // 소수점 첫째 자리까지만 표시
        return "drama/drama_detail";
    }

}



