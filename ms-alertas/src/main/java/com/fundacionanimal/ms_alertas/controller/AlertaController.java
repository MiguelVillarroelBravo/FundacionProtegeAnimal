package com.fundacionanimal.ms_alertas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundacionanimal.ms_alertas.model.Alerta;
import com.fundacionanimal.ms_alertas.service.AlertaService;

@RestController
@RequestMapping("/api/alertas")
@CrossOrigin(origins = "*")
public class AlertaController {

	private final AlertaService alertaService;

	public AlertaController(AlertaService alertaService) {
		this.alertaService = alertaService;
	}

	// GET http://localhost:8083/api/alertas
	@GetMapping
	public ResponseEntity<List<Alerta>> listarAlertasNoLeidas() {
		return ResponseEntity.ok(alertaService.listarAlertasNoLeidas());
	}

	// POST http://localhost:8083/api/alertas
	@PostMapping
	public ResponseEntity<Alerta> crearAlerta(@RequestBody Alerta alerta) {
		Alerta nuevaAlerta = alertaService.crearAlerta(alerta);
		return new ResponseEntity<>(nuevaAlerta, HttpStatus.CREATED);
	}
}
