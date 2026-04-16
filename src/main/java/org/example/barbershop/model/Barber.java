package org.example.barbershop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "barbers")
public class Barber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String specialty;
    private boolean available;

    public Barber() {}

    public Barber(String name, String specialty, boolean available) {
        this.name = name;
        this.specialty = specialty;
        this.available = available;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}