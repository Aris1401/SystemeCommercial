package com.commercial.gestion.controller;

import com.commercial.gestion.model.ArticleFacture;
import com.commercial.gestion.model.Facture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FactureController {
    @PostMapping("/factures/")
    public void ajouterFacture(@RequestBody Facture facture){

    }

    @PostMapping("/factures/articles")
    public void ajouterArticleFacture(@RequestBody ArticleFacture articlefacture){

    }
}
