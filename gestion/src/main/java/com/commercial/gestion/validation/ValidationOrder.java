package com.commercial.gestion.validation;

import com.commercial.gestion.model.Profil;
import com.commercial.gestion.model.ProfilUtilisateur;
import com.commercial.gestion.model.Utilisateur;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ValidationOrder {
    protected Map<Integer, Integer> validationOrder = new LinkedHashMap<Integer, Integer>();

    public ValidationOrder(Map<Integer, Integer> validationOrder) {
        this.validationOrder = validationOrder;
    }

    public Integer[] getAllowedProfiles() {
        Set<Integer> allKeys = validationOrder.keySet();
        return allKeys.toArray(new Integer[0]);
    }

    public Integer[] getCurrentAllowedProfile(int status) {
        if (isAtLastState(status)) return new Integer[]{};

        Integer[] keys = getAllowedProfiles();

        for (int i = 0; i < keys.length - 1; i++) {
            int value = validationOrder.get(keys[i]);

            if (value == status) return new Integer[] { keys[i + 1] };
        }

        if (keys.length > 0 ) return new Integer[]{ keys[0] };
        else return new Integer[]{};
    }

    public boolean isUserAllowed(Utilisateur user) {
        Integer[] profiles = getAllowedProfiles();

        int occurence = 0;
        for (int profile : profiles) {
            if (user.hasProfil(profile)) {
                occurence++;
            }
        }

        return occurence > 0;
    }

    public boolean isAtLastState(int status) {
        Integer[] keys = getAllowedProfiles();

        return validationOrder.get(keys[keys.length - 1]) == status;
    }

    public int canValidate(Utilisateur utilisateur, int status) throws Exception {
        ArrayList<ProfilUtilisateur> allProfilUtilisateurs = utilisateur.getProfilUtilisateur();
        for (ProfilUtilisateur allProfilUtilisateur : allProfilUtilisateurs) {
            Integer[] keys = getAllowedProfiles();

            for (int j = 0; j < keys.length; j++) {
                if (allProfilUtilisateur.getIdProfil() == keys[j]) {
                    if (j > 0) {
                        if (validationOrder.get(keys[j - 1]) == status) {
                            return validationOrder.get(keys[j]);
                        } else {
                            Profil profil = Profil.obtenirProfilById(keys[j - 1]);
                            throw new Exception("Validation attendu d'autre superieur: " + profil.getNom());
                        }
                    } else {
                        return validationOrder.get(keys[j]);
                    }
                }
            }
        }
        return -1;
    }
}
