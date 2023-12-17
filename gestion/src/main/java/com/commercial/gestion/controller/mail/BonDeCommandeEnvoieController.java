package com.commercial.gestion.controller.mail;

import com.commercial.gestion.model.BonDeCommandeEnvoie;
import com.commercial.gestion.model.Utilisateur;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BonDeCommandeEnvoieController {
    @GetMapping("/bondecommandes/envoyer")
    public ArrayList<BonDeCommandeEnvoie> obtenirListeBonDeCommandesEnvoyer(HttpSession session)throws Exception{
        return BonDeCommandeEnvoie.obtenirToutLesBonDeCommandesEnvoyer((Utilisateur)session.getAttribute("user"));
    }
}
