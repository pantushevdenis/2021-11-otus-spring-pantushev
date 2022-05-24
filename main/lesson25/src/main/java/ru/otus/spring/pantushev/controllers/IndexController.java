package ru.otus.spring.pantushev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.pantushev.dto.AuthenticationCommonPageDataDto;
import ru.otus.spring.pantushev.security.AuthenticationUtilsService;

@Controller
public class IndexController {
    private AuthenticationUtilsService authenticationService;

    @Autowired
    public IndexController(AuthenticationUtilsService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationCommonPageDataDto authenticationCommonPageDataDto = authenticationService.toAuthenticationCommonPageDataDto(authentication);
        model.addAttribute("authenticationCommonPageDataDto", authenticationCommonPageDataDto);

        return "index";
    }


}
