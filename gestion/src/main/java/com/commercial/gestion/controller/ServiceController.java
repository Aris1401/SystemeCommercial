package com.commercial.gestion.controller;

import com.commercial.gestion.model.Service;
import com.commercial.gestion.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/services")
    public Service addService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }

    @PostMapping("/services/update")
    public void updateService(@RequestBody Service service) {
        serviceRepository.updateService(service.getIdService(), service.getNom());
    }
}
