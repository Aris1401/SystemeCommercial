package com.commercial.gestion.controller;

import com.commercial.gestion.model.Unite;
import com.commercial.gestion.repository.UniteRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UniteController {
    private final UniteRepository uniteRepository;

    public UniteController(UniteRepository repo) {
        this.uniteRepository = repo;
    }

    @GetMapping("/unites")
    public Iterable<Unite> allUnite() {
        return uniteRepository.findAll();
    }
}
