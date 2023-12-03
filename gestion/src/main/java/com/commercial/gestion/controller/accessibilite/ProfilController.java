package com.commercial.gestion.controller.accessibilite;

import com.commercial.gestion.model.Profil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProfilController {
    @GetMapping("/profils")
    public ArrayList<Profil> allProfils() {
        return Profil.getAllProfils();
    }
}
