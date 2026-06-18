package com.fundacionanimal.donacion.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

	public Optional<Donacion> obtenerPorId(Long id) {
		return donacionRepository.findById(id);
	}
	
	@Transactional
	public Donacion actualizarDonacion(Long id, Donacion datosNuevos) {
		Donacion existente = donacionRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Donacion no encontrada con id: " + id));
	
		existente.setMonto(datosNuevos.getMonto());
		existente.setFecha(datosNuevos.getFecha());
		existente.setEstado(datosNuevos.getEstado());
	
		if (datosNuevos.getDonante() != null && datosNuevos.getDonante().getRutDonante() != null) {
			Donante donantePersistido = donanteService.obtenerOCrearDonante(datosNuevos.getDonante());
			existente.setDonante(donantePersistido);
		}
	
		return donacionRepository.save(existente);
	}
	
	@Transactional
	public void eliminarDonacion(Long id) {
		if (!donacionRepository.existsById(id)) {
			throw new RuntimeException("Donacion no encontrada con id: " + id);
		}
		donacionRepository.deleteById(id);
	}
	
}
