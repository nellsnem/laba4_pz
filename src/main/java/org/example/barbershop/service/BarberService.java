package org.example.barbershop.service;

import org.example.barbershop.model.Barber;
import org.example.barbershop.repository.BarberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BarberService {

    private final BarberRepository barberRepository;

    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    // СЦЕНАРІЙ 3: Отримання списку майстрів
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    public Barber getBarberById(Long id) {
        return barberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Майстра не знайдено з ID: " + id));
    }

    public Barber addBarber(Barber barber) {
        return barberRepository.save(barber);
    }
}