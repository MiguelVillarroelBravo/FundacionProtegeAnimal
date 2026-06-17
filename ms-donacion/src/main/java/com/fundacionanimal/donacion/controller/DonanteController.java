package com.fundacionanimal.donacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundacionanimal.donacion.model.Donante;
import com.fundacionanimal.donacion.service.DonanteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/donantes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DonanteController {

	private final DonanteService donanteService;

	@GetMapping
	public ResponseEntity<List<Donante>> listarDonantes() {
		return ResponseEntity.ok(donanteService.listarDonantes());
	}

	@GetMapping("/{rut}")
	public ResponseEntity<Donante> buscarPorRut(@PathVariable String rut) {
		return donanteService.buscarPorRut(rut)
			.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Donante> crearDonante(@RequestBody Donante donante) {
		Donante nuevoDonante = donanteService.registrarDonante(donante);
		return new ResponseEntity<>(nuevoDonante, HttpStatus.CREATED);
	}
}
