package com.korea.MOVIEBOOK.Drama;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor

public class DramaController {

    private final DramaService dramaService;

    @GetMapping("/drama/dramaList")
    public String dramaList (Model model) {
        List<Drama> dramaList = dramaService.dramaList();
        model.addAttribute("dramaList", dramaList);
        return "drama_list";
    }


}
