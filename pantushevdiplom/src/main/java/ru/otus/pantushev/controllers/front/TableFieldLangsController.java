package ru.otus.pantushev.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TableFieldLangsController {
    @GetMapping("/tableFieldLangs")
    public String listPage(
        @RequestParam("table_name") String tableName,
        @RequestParam("field_name") String fieldName,
        Model model
    ) {
        model.addAttribute("table_name", tableName);
        model.addAttribute("field_name", fieldName);

        return "tableFieldLangs";
    }

}
