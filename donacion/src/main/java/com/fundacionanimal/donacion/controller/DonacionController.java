package com.fundacionanimal.donacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundacionanimal.donacion.model.Donaciones;
import com.fundacionanimal.donacion.service.DonacionService;

@RestController
@RequestMapping("/api/donaciones")
@CrossOrigin(origins = "*")
public class DonacionController 
{
    private final DonacionService donacionService;

    public DonacionController(DonacionService donacionService) {
        this.donacionService = donacionService;
    }

    // GET http://localhost:8081/api/donaciones
    @GetMapping
    public ResponseEntity<List<Donaciones>> listarDonaciones() {
        return ResponseEntity.ok(donacionService.obtenerTodas());
    }

    // POST http://localhost:8081/api/donaciones
    @PostMapping
    public ResponseEntity<Donaciones> crearDonacion(@RequestBody Donaciones donacion) {
        Donaciones nuevaDonacion = donacionService.registrarDonacion(donacion);
        return new ResponseEntity<>(nuevaDonacion, HttpStatus.CREATED);
    }
}
