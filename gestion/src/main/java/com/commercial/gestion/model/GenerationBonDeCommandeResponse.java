package com.commercial.gestion.model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class GenerationBonDeCommandeResponse {
    Timestamp dateCreation;
    ArrayList<BonDeCommande> data;
    String errorMessage;

    public GenerationBonDeCommandeResponse() {
        setDateCreation(new Timestamp(System.currentTimeMillis()));
    }
    public GenerationBonDeCommandeResponse(ArrayList<BonDeCommande> bonDeCommandes) {
        setData(bonDeCommandes);
        setDateCreation(new Timestamp(System.currentTimeMillis()));
    }

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ArrayList<BonDeCommande> getData() {
        return data;
    }

    public void setData(ArrayList<BonDeCommande> data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
