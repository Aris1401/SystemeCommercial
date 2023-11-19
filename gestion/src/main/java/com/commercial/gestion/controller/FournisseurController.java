package com.commercial.gestion.controller;

import com.commercial.gestion.model.Fournisseur;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class FournisseurController {
    @GetMapping("/fournisseurs")
    public ArrayList<Fournisseur> allFournisseur() {
        return Fournisseur.allFournisseur();
    }
}
