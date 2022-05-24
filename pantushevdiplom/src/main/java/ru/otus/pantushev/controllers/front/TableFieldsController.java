package ru.otus.pantushev.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TableFieldsController {
    @GetMapping("/tableFields")
    public String listPage(
        @RequestParam("table_name") String tableName,
        @RequestParam(value="page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value="size", required = false, defaultValue = "30") Integer size,
        Model model
    ) {
        model.addAttribute("table_name", tableName);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "tableFields";
    }

}
