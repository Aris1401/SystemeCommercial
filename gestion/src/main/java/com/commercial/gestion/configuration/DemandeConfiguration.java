package com.commercial.gestion.configuration;

public class DemandeConfiguration {
    public static final int EN_COURS = 0;
    public static final int VALIDER = 10;
    public static final int REFUSER = 20;

    public static final int CLOT = 30;

    public static String getStatusString(int status) {
        if (status >= EN_COURS && status < VALIDER) return "En cours";
        else if (status >= VALIDER && status < REFUSER) return "Valider";
        else if (status >= REFUSER && status < CLOT) return "Refuser";
        else if (status >= CLOT) return "Cloture";
        else return "Aucun etat";
    }
}
