package com.fundacionanimal.ms_voluntariado.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundacionanimal.ms_voluntariado.model.Actividad;
import com.fundacionanimal.ms_voluntariado.model.InscripcionVoluntario;
import com.fundacionanimal.ms_voluntariado.service.VoluntariadoService;

@RestController
@RequestMapping("/api/voluntariado")
@CrossOrigin(origins = "*")
public class VoluntariadoController {

	private final VoluntariadoService voluntariadoService;

	public VoluntariadoController(VoluntariadoService voluntariadoService) {
		this.voluntariadoService = voluntariadoService;
	}

	// GET http://localhost:8085/api/voluntariado/actividades
	@GetMapping("/actividades")
	public ResponseEntity<List<Actividad>> listarActividadesDisponibles() {
		return ResponseEntity.ok(voluntariadoService.listarActividadesDisponibles());
	}

	// POST http://localhost:8085/api/voluntariado/actividades
	@PostMapping("/actividades")
	public ResponseEntity<Actividad> crearActividad(@RequestBody Actividad actividad) {
		Actividad nuevaActividad = voluntariadoService.registrarActividad(actividad);
		return new ResponseEntity<>(nuevaActividad, HttpStatus.CREATED);
	}

	// POST http://localhost:8085/api/voluntariado/inscribir
	@PostMapping("/inscribir")
	public ResponseEntity<InscripcionVoluntario> inscribirVoluntario(@RequestBody InscripcionVoluntario inscripcion) {
		InscripcionVoluntario nuevaInscripcion = voluntariadoService.inscribirVoluntario(inscripcion);
		if (nuevaInscripcion == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return new ResponseEntity<>(nuevaInscripcion, HttpStatus.CREATED);
	}
}
