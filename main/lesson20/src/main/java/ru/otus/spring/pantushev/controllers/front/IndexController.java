package ru.otus.spring.pantushev.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/")
    public String listPage(Model model) {
        return "index";
    }


}
