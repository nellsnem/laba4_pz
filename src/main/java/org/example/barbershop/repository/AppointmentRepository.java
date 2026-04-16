package org.example.barbershop.repository;

import org.example.barbershop.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByBarberIdAndDateTime(Long barberId, LocalDateTime dateTime);
}