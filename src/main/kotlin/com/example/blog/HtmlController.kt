package com.example.blog

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HtmlController {

    @GetMapping("/")
    fun blog(model: Model): String {
        // Here we import the org.springframework.ui.set extension function in order to be able to write
        // model["title"] = "Blog"
        // instead of
        // model.addAttribute("title", "Blog").
        model["title"] = "Blog"
        return "blog"
    }

}