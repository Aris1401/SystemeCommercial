package com.commercial.gestion.controller;

import com.commercial.gestion.model.Service;
import com.commercial.gestion.repository.ServiceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ServiceController {
    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository repo) {
        this.serviceRepository = repo;
    }

    @GetMapping("/services")
    public Iterable<Service> allServices() {
        return serviceRepository.findAll();
    }

    @GetMapping("/services/{id}")
    public Optional<Service> getService(@PathVariable("id") int id) {
        return serviceRepository.findById((long) id);
    }
}
