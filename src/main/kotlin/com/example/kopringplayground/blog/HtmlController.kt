package com.example.kopringplayground.blog

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {
    @GetMapping("/")
    fun blog(model: Model): String {
        // model.addAttribute("title", "Blog")와 같음
        // set operator가 확장함수로 정의되어 있어 사용 가능
        model["title"] = "Blog"
        return "blog"
    }
}
