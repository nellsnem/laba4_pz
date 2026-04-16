package org.example.barbershop.controller;

import org.example.barbershop.model.Service;
import org.example.barbershop.service.ServiceCatalogService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    private final ServiceCatalogService serviceCatalogService;

    public ServiceController(ServiceCatalogService serviceCatalogService) {
        this.serviceCatalogService = serviceCatalogService;
    }

    // СЦЕНАРІЙ 5: GET /api/services
    @GetMapping
    public List<Service> getAllServices() {
        return serviceCatalogService.getAllServices();
    }

    // Додати послугу (для тестів)
    @PostMapping
    public Service addService(@RequestBody Service service) {
        return serviceCatalogService.addService(service);
    }
}