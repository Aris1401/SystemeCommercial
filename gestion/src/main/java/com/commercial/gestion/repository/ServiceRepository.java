package com.commercial.gestion.repository;

import com.commercial.gestion.model.Service;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Service SET nom = ?2 WHERE idService = ?1")
    public void updateService(int idService, String nomService);
}
