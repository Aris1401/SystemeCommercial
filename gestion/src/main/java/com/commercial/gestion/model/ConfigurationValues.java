package com.commercial.gestion.model;

import com.commercial.gestion.aris.bdd.generic.GenericDAO;
import com.commercial.gestion.dbAccess.ConnectTo;

import java.sql.Connection;
import java.util.ArrayList;

public class ConfigurationValues {
    String designation;
    String valeur;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public static String getConfigurationValue(String key) {
        GenericDAO<ConfigurationValues> configurationValuesGenericDAO = new GenericDAO<>(ConfigurationValues.class);

        try {
            Connection c = ConnectTo.postgreS();

            configurationValuesGenericDAO.addToSelection("designation", key, "");
            ArrayList<ConfigurationValues> configurationValues = configurationValuesGenericDAO.getFromDatabase(c);

            c.close();

            return configurationValues.isEmpty() ? null : configurationValues.get(0).getValeur();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
