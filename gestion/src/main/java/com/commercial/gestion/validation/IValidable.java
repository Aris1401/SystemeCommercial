package com.commercial.gestion.validation;

import com.commercial.gestion.model.Utilisateur;

public interface IValidable {
    public Integer[] getCanBeValidated();
    public boolean valider(Utilisateur user) throws Exception;
    public boolean getIsValidationComplete();
}
