package org.example.barbershop.controller;

import org.example.barbershop.model.Barber;
import org.example.barbershop.service.BarberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {

    private final BarberService barberService;

    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    // СЦЕНАРІЙ 3: GET /api/barbers
    @GetMapping
    public List<Barber> getAllBarbers() {
        return barberService.getAllBarbers();
    }

    // Додати майстра (для тестів)
    @PostMapping
    public Barber addBarber(@RequestBody Barber barber) {
        return barberService.addBarber(barber);
    }
}