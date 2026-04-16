package org.example.barbershop.controller;

import org.example.barbershop.model.Appointment;
import org.example.barbershop.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // СЦЕНАРІЙ 1: POST /api/appointments
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody Map<String, Object> body) {
        try {
            String clientName = (String) body.get("clientName");
            Long barberId = Long.valueOf(body.get("barberId").toString());
            Long serviceId = Long.valueOf(body.get("serviceId").toString());
            LocalDateTime dateTime = LocalDateTime.parse(body.get("dateTime").toString());

            Appointment appointment = appointmentService.createAppointment(
                    clientName, barberId, serviceId, dateTime
            );
            return ResponseEntity.ok(appointment);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Конфлікт")) {
                return ResponseEntity.status(409).body(e.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // СЦЕНАРІЙ 2: GET /api/appointments/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(appointmentService.getAppointmentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    // СЦЕНАРІЙ 4: DELETE /api/appointments/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Запис успішно видалено");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}