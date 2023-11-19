package com.commercial.gestion.configuration;

public class DemandeBesoinAchatConfiguration {
    public static final int EN_COURS = 0;
    public static final int VALIDER = 10;
    public static final int REFUSER = 20;

    public static final int CLOT = 30;

    public static String getStatusString(int status) {
        return switch (status) {
            case EN_COURS -> "En cours";
            case VALIDER -> "Valider";
            case REFUSER -> "Refusee";
            case CLOT -> "Clot";
            default -> "";
        };
    }
}
