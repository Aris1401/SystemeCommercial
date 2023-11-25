package com.commercial.gestion.validation;

import com.commercial.gestion.configuration.DemandeConfiguration;

import java.util.*;

public class ValidationBesoinAchatConfiguration extends ValidationOrder {
    public ValidationBesoinAchatConfiguration() {
        super(new LinkedHashMap<>() {{
            put(6, DemandeConfiguration.VALIDER);
            // add more entries as needed
        }});
    }
}
