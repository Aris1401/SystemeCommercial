package com.commercial.gestion.controller;

import com.commercial.gestion.configuration.DemandeBesoinAchatConfiguration;
import com.commercial.gestion.model.ArticleBesoinAchat;
import com.commercial.gestion.model.BesoinAchat;
import com.commercial.gestion.response.ArticleQuantiteResponse;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BesoinAchatController {
    @GetMapping("/besoinsachat/open")
    public ArrayList<BesoinAchat> allOpenBesoinsAchat() {
        return BesoinAchat.allOpenBesoinAchat();
    }

    @GetMapping("/besoinsachat/{idBesoinAchat}")
    public BesoinAchat getBesoinAchat(@PathVariable("idBesoinAchat") int idBesoinAchat) {
        return BesoinAchat.getBesoinAchatById(idBesoinAchat);
    }

    @PostMapping("/besoinsachat")
    public BesoinAchat insertBesoinAchat(@RequestBody BesoinAchat besoin) {
        besoin.dontSave("idBesoinAchat");
        besoin.setDateBesoin(new Timestamp(System.currentTimeMillis()));
        besoin.setIdBesoinAchat(besoin.saveId());
        besoin.setStatusBesoin(DemandeBesoinAchatConfiguration.EN_COURS);

        return besoin;
    }

    @GetMapping("/besoinsachat/{idBesoinAchat}/articles")
    public List<ArticleQuantiteResponse> getBesoinAchatArticles(@PathVariable("idBesoinAchat") int idBesoinAchat) {
        BesoinAchat besoinAchat = BesoinAchat.getBesoinAchatById(idBesoinAchat);
        System.out.println(besoinAchat.getIdBesoinAchat());
        return besoinAchat.allArticles();
    }

    @PostMapping("/articlesbesoinachat")
    public ArticleBesoinAchat createArticleBesoinAchat(@RequestBody ArticleBesoinAchat articleBesoinAchat) {
        articleBesoinAchat.dontSave("idArticleBesoinAchat");
        articleBesoinAchat.save();

        return articleBesoinAchat;
    }
}
