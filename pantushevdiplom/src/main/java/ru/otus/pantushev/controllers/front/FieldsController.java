package ru.otus.pantushev.controllers.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FieldsController {
    @GetMapping("/fields")
    public String listPage(
        @RequestParam(value="page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value="size", required = false, defaultValue = "30") Integer size,
        Model model
    ) {
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "fields";
    }

    @GetMapping("/fields/edit/{id}")
    public String getEdit(@PathVariable("id") String id, Model model) {
        model.addAttribute("isEdit", true);
        model.addAttribute("id", id);
        return "fieldEdit";
    }

    @GetMapping("/fields/add")
    public String add(Model model) {
        model.addAttribute("isEdit", false);
        model.addAttribute("id", "");
        model.addAttribute("dataTypeFlg", "CHAR");
        model.addAttribute("signedSw", false);
        model.addAttribute("fldPrecision", 30);
        model.addAttribute("fldScale", 0);
        return "fieldEdit";
    }

}
