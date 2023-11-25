package com.commercial.gestion.response;

import com.commercial.gestion.model.BonDeCommande;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Response<T> {
    Timestamp dateCreation;
    ArrayList<T> data;
    String errorMessage;

    public Timestamp getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void addToData(T dataItem) {
        if (this.data == null) data = new ArrayList<>();
        data.add(dataItem);
    }
}
