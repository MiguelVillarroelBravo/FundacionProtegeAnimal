package com.fundacionanimal.ms_apadrinamientos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundacionanimal.ms_apadrinamientos.model.Apadrinamiento;
import com.fundacionanimal.ms_apadrinamientos.service.ApadrinamientoService;

@RestController
@RequestMapping("/api/apadrinamientos")
@CrossOrigin(origins = "*")
public class ApadrinamientoController {

	private final ApadrinamientoService apadrinamientoService;

	public ApadrinamientoController(ApadrinamientoService apadrinamientoService) {
		this.apadrinamientoService = apadrinamientoService;
	}

	// POST http://localhost:8084/api/apadrinamientos
	@PostMapping
	public ResponseEntity<Apadrinamiento> crearApadrinamiento(@RequestBody Apadrinamiento apadrinamiento) {
		Apadrinamiento nuevoApadrinamiento = apadrinamientoService.crearApadrinamiento(apadrinamiento);
		return new ResponseEntity<>(nuevoApadrinamiento, HttpStatus.CREATED);
	}

	// GET http://localhost:8084/api/apadrinamientos/padrino/{rut}
	@GetMapping("/padrino/{rut}")
	public ResponseEntity<List<Apadrinamiento>> listarActivosPorRutPadrino(@PathVariable String rut) {
		return ResponseEntity.ok(apadrinamientoService.buscarActivosPorRutPadrino(rut));
	}
}
