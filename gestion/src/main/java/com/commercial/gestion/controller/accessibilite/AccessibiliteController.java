package com.commercial.gestion.controller.accessibilite;

import com.commercial.gestion.model.Profil;
import com.commercial.gestion.model.Utilisateur;
import com.commercial.gestion.model.accessibilte.ProfilNonAccessiblePage;
import com.commercial.gestion.response.Response;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/accessibilite")
public class AccessibiliteController {
    @GetMapping("/page")
    public static Response<Profil> getProfilsAccessibiltes(@RequestParam("page") String page) {
        Response<Profil> profilResponse = new Response<>();

        ArrayList<Profil> profils = ProfilNonAccessiblePage.getProfilsUnauthorizedForPage(page);
        profilResponse.setData(profils);

        return profilResponse;
    }

    @GetMapping("/restrict/profil/{profil}")
    public void restrictProfilToPage(@PathVariable("profil") int idProfil, @RequestParam("page") String page) {
        ProfilNonAccessiblePage.removeAccessiblityForProfil(idProfil, page);
    }

    @PostMapping("/filter")
    public ArrayList<String> filterRoutes(@RequestBody ArrayList<String> routes, HttpSession session) {
        ArrayList<Profil> profils = new ArrayList<>();

        if (session.getAttribute("user") != null) {
            Utilisateur utilisateur = (Utilisateur) session.getAttribute("user");
            profils = utilisateur.getProfils();
        }

        if (profils.isEmpty()) return new ArrayList<String>();

        ArrayList<String> newRoutes = routes;
        for (Profil profil : profils) {
            newRoutes = ProfilNonAccessiblePage.filterAccessibility(profil.getIdProfil(), newRoutes);
        }
        System.out.println(newRoutes);

        return newRoutes;
    }
}
