package com.commercial.gestion.controller;

import com.commercial.gestion.model.LoginInfomations;
import com.commercial.gestion.model.Utilisateur;
import com.commercial.gestion.response.Response;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenficationController {
    @PostMapping("/login")
    public Response<Utilisateur> login(@RequestBody LoginInfomations loginInfomations, HttpSession session) {
        Utilisateur user = Utilisateur.connect(loginInfomations.getEmail(), loginInfomations.getMotDePasse());

        Response<Utilisateur> utilisateurResponse = new Response<>();
        if (user != null) {
            session.setAttribute("user", user);
            utilisateurResponse.addToData(user);
        } else utilisateurResponse.setErrorMessage("Mot de passe ou email invalider");
        return utilisateurResponse;
    }

    @PostMapping("/checkAuth")
    public Utilisateur checkAuth(HttpSession session) {
        return (Utilisateur) session.getAttribute("user");
    }
}
