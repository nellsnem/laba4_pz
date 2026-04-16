package org.example.barbershop.service;

import org.example.barbershop.model.Appointment;
import org.example.barbershop.model.Barber;
import org.example.barbershop.model.Service;
import org.example.barbershop.repository.AppointmentRepository;
import java.time.LocalDateTime;

@org.springframework.stereotype.Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final BarberService barberService;
    private final ServiceCatalogService serviceCatalogService;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              BarberService barberService,
                              ServiceCatalogService serviceCatalogService) {
        this.appointmentRepository = appointmentRepository;
        this.barberService = barberService;
        this.serviceCatalogService = serviceCatalogService;
    }

    // СЦЕНАРІЙ 1: Запис клієнта до майстра
    public Appointment createAppointment(String clientName, Long barberId,
                                         Long serviceId, LocalDateTime dateTime) {
        Barber barber = barberService.getBarberById(barberId);
        Service service = serviceCatalogService.getServiceById(serviceId);

        if (!barber.isAvailable()) {
            throw new RuntimeException("Майстер зараз недоступний!");
        }
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Не можна записатись в минулу дату!");
        }
        if (appointmentRepository.existsByBarberIdAndDateTime(barberId, dateTime)) {
            throw new RuntimeException("Конфлікт: Цей майстер вже зайнятий на цей час!");
        }
        Appointment appointment = new Appointment(clientName, dateTime, barber, service);
        return appointmentRepository.save(appointment);
    }

    // СЦЕНАРІЙ 2: Перегляд запису за ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Запис не знайдено з ID: " + id));
    }

    // СЦЕНАРІЙ 4: Видалення запису
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Запис не знайдено з ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}