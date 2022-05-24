package ru.otus.pantushev.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TableLangsController {
    @GetMapping("/tableLangs")
    public String listPage(
        @RequestParam("table_name") String tableName,
        Model model
    ) {
        model.addAttribute("table_name", tableName);

        return "tableLangs";
    }

    @GetMapping("/tableLangs/edit")
    public String getEdit(
        @RequestParam("table_name") String tableName,
        @RequestParam("language_cd") String languageCd,
        Model model
    ) {
        model.addAttribute("isEdit", true);
        model.addAttribute("tableName", tableName);
        model.addAttribute("languageCd", languageCd);
        return "tableLangEdit";
    }

    @GetMapping("/tableLangs/add")
    public String add(
        @RequestParam("table_name") String tableName,
        Model model
    ) {
        model.addAttribute("tableName", tableName);
        model.addAttribute("isEdit", false);
        model.addAttribute("languageCd", "RUS");

        return "tableLangEdit";
    }


}
