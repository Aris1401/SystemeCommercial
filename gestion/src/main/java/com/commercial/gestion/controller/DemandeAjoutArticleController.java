package com.commercial.gestion.controller;

import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.model.Article;
import com.commercial.gestion.model.DemandeAjoutArticle;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
public class DemandeAjoutArticleController {
    @PostMapping("/demandeajoutarticle")
    public void ajouterDemande(@RequestBody DemandeAjoutArticle demandeAjoutArticle) {
        demandeAjoutArticle.setDateLivraison(new Timestamp(System.currentTimeMillis()));
        demandeAjoutArticle.setStatusDemande(DemandeConfiguration.EN_COURS);
        demandeAjoutArticle.dontSave("idDemandeAjoutArticle");

        demandeAjoutArticle.save();
    }

        @GetMapping("/demandeajoutarticle/{id}/valider")
    public void validerDemande(@PathVariable("id") int id) {
        DemandeAjoutArticle demandeAjoutArticle = DemandeAjoutArticle.getById(id);
        Article.insertArticleByDemande(demandeAjoutArticle);
    }

    @GetMapping("/demandeajoutarticle")
    public ArrayList<DemandeAjoutArticle> allDemandes() {
        return DemandeAjoutArticle.allDemandeAjoutArticle();
    }
}
