package com.commercial.gestion.controller;

import com.commercial.gestion.model.ArticleBonDeLivraison;
import com.commercial.gestion.model.BonDeLivraison;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BonDeLivraisonController {

    @PostMapping("/bondelivraisons/")
    public void ajouterBonDeLivraison(@RequestBody BonDeLivraison bonDeLivraison){

    }

    @PostMapping("/bondelivraisons/article")
    public void ajouterArticleBonDeLivraison(@RequestBody ArticleBonDeLivraison articleBonDeLivraison){

    }
}
