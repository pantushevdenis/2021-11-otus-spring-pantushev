package ru.otus.pantushev.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FieldLangsController {
    @GetMapping("/fieldLangs")
    public String listPage(
        @RequestParam("field_name") String fieldName,
        Model model
    ) {
        model.addAttribute("field_name", fieldName);

        return "fieldLangs";
    }

}
