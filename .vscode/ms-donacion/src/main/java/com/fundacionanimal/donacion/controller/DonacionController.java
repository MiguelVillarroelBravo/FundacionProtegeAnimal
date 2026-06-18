package com.fundacionanimal.donacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundacionanimal.donacion.model.Donacion;
import com.fundacionanimal.donacion.service.DonacionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/donaciones")
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


    // GET http://localhost:8081/api/donaciones/{id}
@GetMapping("/{id}")
public ResponseEntity<Donacion> obtenerDonacion(@PathVariable Long id) {
    return donacionService.obtenerPorId(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}

// PUT http://localhost:8081/api/donaciones/{id}
@PutMapping("/{id}")
public ResponseEntity<Donacion> actualizarDonacion(@PathVariable Long id, @RequestBody Donacion donacion) {
    try {
        Donacion actualizada = donacionService.actualizarDonacion(id, donacion);
        return ResponseEntity.ok(actualizada);
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}

// DELETE http://localhost:8081/api/donaciones/{id}
@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminarDonacion(@PathVariable Long id) {
    try {
        donacionService.eliminarDonacion(id);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}
}
