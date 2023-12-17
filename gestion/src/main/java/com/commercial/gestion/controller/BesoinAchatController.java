package com.commercial.gestion.controller;

import com.commercial.gestion.configuration.DemandeConfiguration;
import com.commercial.gestion.model.*;
import com.commercial.gestion.response.ArticleQuantiteResponse;
import com.commercial.gestion.response.GenerationBonDeCommandeResponse;
import com.commercial.gestion.response.ValidationBesoinAchatResponse;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/besoinsachat/closed")
    public ArrayList<BesoinAchat> allClosedBesoinsAchat() {
        return BesoinAchat.allClosedBesoinAchat();
    }

    @GetMapping("/besoinsachat/nature")
    public ArrayList<BesoinAchatEnNature> besoinAchatsEnNature() {
        return BesoinAchatEnNature.obtenirBesoinsEnNature();
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
        besoin.setStatusBesoin(DemandeConfiguration.EN_COURS);

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

    @GetMapping("/besoinsachat/{idBesoinAchat}/valider")
    public ValidationBesoinAchatResponse validerBesoinAchat(@PathVariable("idBesoinAchat") int idBesoinAchat, HttpSession session) {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        BesoinAchat besoinAchat = BesoinAchat.getBesoinAchatById(idBesoinAchat);

        ValidationBesoinAchatResponse response = new ValidationBesoinAchatResponse();
        response.addToData(besoinAchat);
        try {
            besoinAchat.valider(user);
        } catch (Exception e) {
            response.setErrorMessage(e.getMessage());
        }

        return response;
    }

    @GetMapping("/besoinsachat/{idBesoinAchat}/bondecommande/generer")
    public GenerationBonDeCommandeResponse genererBonDeCommnades(@PathVariable("idBesoinAchat") int idBesoinAchat) {
        BesoinAchat besoinAchat = BesoinAchat.getBesoinAchatById(idBesoinAchat);

        GenerationBonDeCommandeResponse response = new GenerationBonDeCommandeResponse();
        try {
             ArrayList<BonDeCommande> bonDeCommandes = besoinAchat.genererBonDeCommandes();
             response.setData(bonDeCommandes);
        } catch (Exception e) {
            response.setErrorMessage(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
