package com.commercial.gestion.model.accessibilte;

import com.commercial.gestion.aris.bdd.annotations.ExcludeFromInsertion;
import com.commercial.gestion.aris.bdd.annotations.PrimaryKey;
import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;
import com.commercial.gestion.model.Profil;

import java.sql.Connection;
import java.util.ArrayList;

public class ProfilNonAccessiblePage extends GenericDAO {
    @PrimaryKey
    @ExcludeFromInsertion
    int idNonAccessiblePage;
    int idProfile;
    String nonAccessiblePage;

    public int getIdNonAccessiblePage() {
        return idNonAccessiblePage;
    }

    public void setIdNonAccessiblePage(int idNonAccessiblePage) {
        this.idNonAccessiblePage = idNonAccessiblePage;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }

    public String getNonAccessiblePage() {
        return nonAccessiblePage;
    }

    public void setNonAccessiblePage(String nonAccessiblePage) {
        this.nonAccessiblePage = nonAccessiblePage;
    }

    // Ajouter une page non accessible pour un profil
    public static void removeAccessiblityForProfil(int idProfil, String page) {
        ProfilNonAccessiblePage profilNonAccessiblePage = new ProfilNonAccessiblePage();
        profilNonAccessiblePage.setIdProfile(idProfil);
        profilNonAccessiblePage.setNonAccessiblePage(page);

        try {
            Connection c = ConnectTo.postgreS();

            profilNonAccessiblePage.saveInDatabse(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Remettre l'autoriasation d'access
    public static void restoreAccessbilityForProfil(int idProfil, String page) {
        ProfilNonAccessiblePage profilNonAccessiblePage = new ProfilNonAccessiblePage();

        try {
            Connection c = ConnectTo.postgreS();

            profilNonAccessiblePage.addToSelection("idProfile", idProfil, "");
            profilNonAccessiblePage.addToSelection("nonAccessiblePage", page, "and");

            profilNonAccessiblePage.deleteFromDatabase(c);

            c.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Filtering the accessible pages for the current profile
    public static boolean isPageAccessibleBy(int idProfil, String page) {
        ProfilNonAccessiblePage profilNonAccessiblePage = new ProfilNonAccessiblePage();

        try {
            Connection c = ConnectTo.postgreS();

            profilNonAccessiblePage.addToSelection("idProfile", idProfil, "");
            profilNonAccessiblePage.addToSelection("nonAccessiblePage", page, "and");

            // Getting all the instances from the database
            var profilNonAccessiblePages = profilNonAccessiblePage.getFromDatabase(c);
            c.close();

            if (!profilNonAccessiblePages.isEmpty()) return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
    public static ArrayList<String> filterAccessibility(int idProfil, ArrayList<String> allPages) {
        ArrayList<String> accessiblePages = new ArrayList<>();

        for (String page : allPages) {
            if (isPageAccessibleBy(idProfil, page)) {
                accessiblePages.add(page);
            }
        }

        return accessiblePages;
    }

    public static ArrayList<Profil> getProfilsUnauthorizedForPage(String page) {
        ProfilNonAccessiblePage profilNonAccessiblePage = new ProfilNonAccessiblePage();

        ArrayList<Profil> profils = new ArrayList<>();
        try {
            Connection c = ConnectTo.postgreS();

            profilNonAccessiblePage.addToSelection("nonAccessiblePage", page, "");
            ArrayList<ProfilNonAccessiblePage> profilNonAccessiblePages = profilNonAccessiblePage.getFromDatabase(c);

            c.close();

            for (ProfilNonAccessiblePage p : profilNonAccessiblePages) {
                profils.add(Profil.obtenirProfilById(p.getIdProfile()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return profils;
    }
}
