package com.commercial.gestion.repository;

import com.commercial.gestion.model.ModeDePaiement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModeDePaiementRepository extends CrudRepository<ModeDePaiement, Long> {
}
