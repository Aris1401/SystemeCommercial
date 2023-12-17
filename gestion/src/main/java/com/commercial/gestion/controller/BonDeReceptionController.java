package com.commercial.gestion.controller;

import com.commercial.gestion.model.ArticleBonDeReception;
import com.commercial.gestion.model.BonDeReception;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BonDeReceptionController {
    @PostMapping("/bondereceptions/")
    public void ajouterBonDeReception(@RequestBody BonDeReception bonDeReception){

    }

    @PostMapping("/bondereceptions/article")
    public void ajouterArticleBonDeReception(@RequestBody ArticleBonDeReception articleBonDeReception){

    }
}
