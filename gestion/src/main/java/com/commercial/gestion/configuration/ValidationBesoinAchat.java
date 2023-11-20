package com.commercial.gestion.configuration;

import com.commercial.gestion.model.BesoinAchat;
import com.commercial.gestion.model.ProfilUtilisateur;
import com.commercial.gestion.model.Utilisateur;

import java.util.*;

public class ValidationBesoinAchat extends DemandeConfiguration {
    private static Map<Integer, Integer> allDemandes = new LinkedHashMap<Integer, Integer>() {{
        put(5, 8);
        put(10, 5);
    }};

    public static int canValidate(Utilisateur utilisateur, BesoinAchat besoinAchat) throws Exception {
        ArrayList<ProfilUtilisateur> allProfilUtilisateurs = utilisateur.getProfil();
        for (int i = 0; i < allProfilUtilisateurs.size(); i++) {
            Set<Integer> allKeys = allDemandes.keySet();
            Integer[] keys = allKeys.toArray(new Integer[0]);
            for (int j = 0; j < keys.length; j++) {
                if (allProfilUtilisateurs.get(i).getIdProfil() == keys[j]) {
                    if (j > 0) {
                        if (allDemandes.get(keys[j - 1]) == besoinAchat.getStatusBesoin()) {
                            return allDemandes.get(keys[j]);
                        } else {
                            throw new Exception("Validation attendu d'autre superieur");
                        }
                    } else {
                        return allDemandes.get(keys[j]);
                    }
                }
            }
        }
        return -1;
    }
}
