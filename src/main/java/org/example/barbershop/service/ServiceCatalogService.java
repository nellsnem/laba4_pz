package org.example.barbershop.service;

import org.example.barbershop.model.Service;
import org.example.barbershop.repository.ServiceRepository;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceCatalogService {

    private final ServiceRepository serviceRepository;

    public ServiceCatalogService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // СЦЕНАРІЙ 5: Отримання каталогу послуг
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service getServiceById(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Послугу не знайдено з ID: " + id));
    }

    public Service addService(Service service) {
        return serviceRepository.save(service);
    }
}