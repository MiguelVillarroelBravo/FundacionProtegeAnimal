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

import com.fundacionanimal.donacion.model.Donacion;
import com.fundacionanimal.donacion.service.DonacionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/donaciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DonacionController {
    private final DonacionService donacionService;

    // GET http://localhost:8081/api/donaciones
    @GetMapping
    public ResponseEntity<List<Donacion>> listarDonaciones() {
        return ResponseEntity.ok(donacionService.obtenerTodas());
    }

    // POST http://localhost:8081/api/donaciones
    @PostMapping
    public ResponseEntity<Donacion> crearDonacion(@RequestBody Donacion donacion) {
        Donacion nuevaDonacion = donacionService.registrarDonacion(donacion);
        return new ResponseEntity<>(nuevaDonacion, HttpStatus.CREATED);
    }
}
