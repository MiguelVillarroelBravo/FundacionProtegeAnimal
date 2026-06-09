package com.fundacionanimal.donacion.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundacionanimal.donacion.model.Donacion;
import com.fundacionanimal.donacion.model.Donante;
import com.fundacionanimal.donacion.repository.DonacionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonacionService {

	private final DonacionRepository donacionRepository;
	private final DonanteService donanteService;

	public List<Donacion> obtenerTodas() {
		return donacionRepository.findAll();
	}

	@Transactional
	public Donacion registrarDonacion(Donacion donacion) {
		Donante donante = donacion.getDonante();
		if (donante == null || donante.getRutDonante() == null || donante.getRutDonante().isBlank()) {
			throw new IllegalArgumentException("El donante y su RUT son obligatorios");
		}

		Donante donantePersistido = donanteService.obtenerOCrearDonante(donante);
		donacion.setDonante(donantePersistido);

		if (donacion.getFecha() == null) {
			donacion.setFecha(LocalDate.now());
		}
		if (donacion.getEstado() == null) {
			donacion.setEstado("Aprobada");
		}

		return donacionRepository.save(donacion);
	}
}
