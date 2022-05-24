package ru.otus.spring.pantushev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.spring.pantushev.domain.Jenre;
import ru.otus.spring.pantushev.dto.AuthenticationCommonPageDataDto;
import ru.otus.spring.pantushev.repositories.JenresRepository;
import ru.otus.spring.pantushev.security.AuthenticationUtilsService;

import java.util.List;

@Controller
public class JenresController {
    private final JenresRepository jenresRepository;
    private final AuthenticationUtilsService authenticationService;


    @Autowired
    public JenresController(JenresRepository jenresRepository, AuthenticationUtilsService authenticationService) {
        this.jenresRepository = jenresRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/jenres")
    public String listPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthenticationCommonPageDataDto authenticationCommonPageDataDto = authenticationService.getAuthenticationCommonPageDataDto(authentication);
        model.addAttribute("authenticationCommonPageDataDto", authenticationCommonPageDataDto);

        List<Jenre> jenres = jenresRepository.findAll();
        model.addAttribute("jenres", jenres);
        return "jenres";
    }
}
