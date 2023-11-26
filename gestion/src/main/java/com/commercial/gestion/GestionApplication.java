package com.commercial.gestion;

import com.commercial.gestion.model.stock.LigneStock;
import com.commercial.gestion.model.stock.MouvementStock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Timestamp;

@SpringBootApplication
@ComponentScan(basePackages = "com.commercial.gestion")
public class GestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionApplication.class, args);
	}

}
