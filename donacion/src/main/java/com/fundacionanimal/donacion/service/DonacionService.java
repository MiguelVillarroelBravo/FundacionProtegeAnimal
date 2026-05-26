package com.fundacionanimal.donacion.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fundacionanimal.donacion.model.Donaciones;
import com.fundacionanimal.donacion.repository.DonacionRepository;

@Service
public class DonacionService 
{
    private final DonacionRepository donacionRepository;

    // Inyección de dependencias por constructor
    public DonacionService(DonacionRepository donacionRepository) {
        this.donacionRepository = donacionRepository;
    }

    public List<Donaciones> obtenerTodas() {
        return donacionRepository.findAll();
    }

    public Donaciones registrarDonacion(Donaciones donacion) {
        if (donacion.getFecha() == null) {
            donacion.setFecha(LocalDate.now()); // Asigna fecha de hoy por defecto
        }
        if (donacion.getEstado() == null) {
            donacion.setEstado("Aprobada"); // Simula Transbank aprobado por defecto
        }
        return donacionRepository.save(donacion);
    }
}
