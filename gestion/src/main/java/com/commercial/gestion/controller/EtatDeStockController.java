package com.commercial.gestion.controller;

import com.commercial.gestion.model.stock.EtatDeStock;
import com.commercial.gestion.model.stock.MouvementStock;
import com.commercial.gestion.model.stock.dto.EntreeDTO;
import com.commercial.gestion.model.stock.dto.SortieDTO;
import com.commercial.gestion.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtatDeStockController {
    @GetMapping("/etatdestock")
    public Response<EtatDeStock> getEtatDeStock() {
        Response<EtatDeStock> etatDeStockResponse = new Response<>();
        etatDeStockResponse.addToData(EtatDeStock.obtenirEtatStock(-1));
        return etatDeStockResponse;
    }

    @PostMapping("/etatdestock/entree")
    public Response<MouvementStock> ajouterEntree(@RequestBody EntreeDTO entreeDTO) {
        Response<MouvementStock> mouvementStockResponse = new Response<>();
        try {
            MouvementStock mouvementStock = MouvementStock.faireUneEntree(entreeDTO);
            mouvementStockResponse.addToData(mouvementStock);
        } catch (Exception e) {
            mouvementStockResponse.setErrorMessage(e.getMessage());
            e.printStackTrace();
        }
        return mouvementStockResponse;
    }

    @PostMapping("/etatdestock/sortie")
    public Response<MouvementStock> ajouterSortie(@RequestBody SortieDTO sortieDTO) {
        Response<MouvementStock> mouvementStockResponse = new Response<>();
        try {
            MouvementStock mouvementStock = MouvementStock.faireUneSortie(sortieDTO);
            mouvementStockResponse.addToData(mouvementStock);
        } catch (Exception e) {
            mouvementStockResponse.setErrorMessage(e.getMessage());
            e.printStackTrace();
        }
        return mouvementStockResponse;
    }
}
