package ru.otus.pantushev.controllers.front;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class TablesController {
    @GetMapping("/tables")
    public String listPage(
        @RequestParam(value="page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value="size", required = false, defaultValue = "30") Integer size,
        Model model
    ) {
        log.info("begin listPage");

        model.addAttribute("page", page);
        model.addAttribute("size", size);

        log.info("end listpage");

        return "tables";
    }

    @GetMapping("/tables/edit/{id}")
    public String getEdit(@PathVariable("id") String id, Model model) {
        model.addAttribute("isEdit", true);
        model.addAttribute("id", id);
        return "tableEdit";
    }

    @GetMapping("/tables/add")
    public String add(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("id", "");
        model.addAttribute("keyTypeFlg", "USR");
        return "tableEdit";
    }



}
