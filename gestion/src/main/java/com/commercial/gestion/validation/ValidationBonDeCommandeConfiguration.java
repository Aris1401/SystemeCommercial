package com.commercial.gestion.validation;

import com.commercial.gestion.configuration.DemandeConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;

public class ValidationBonDeCommandeConfiguration extends ValidationOrder {

    public ValidationBonDeCommandeConfiguration() {
        super(new LinkedHashMap<>() {{
            put(3, 2);
            put(4, 5);
            put(5, DemandeConfiguration.VALIDER);
            // add more entries as needed
        }});
    }
}
