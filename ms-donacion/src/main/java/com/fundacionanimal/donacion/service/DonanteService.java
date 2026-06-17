package com.fundacionanimal.donacion.service;

import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fundacionanimal.donacion.model.Donante;
import com.fundacionanimal.donacion.repository.DonanteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonanteService {

	private final DonanteRepository donanteRepository;

	public List<Donante> listarDonantes() {
		return donanteRepository.findAll();
	}

	public Donante registrarDonante(Donante donante) {
		return donanteRepository.save(donante);
	}

	public Optional<Donante> buscarPorRut(String rutDonante) {
		return donanteRepository.findById(rutDonante);
	}

	public Donante obtenerOCrearDonante(Donante donante) {
		Optional<Donante> existente = donanteRepository.findById(donante.getRutDonante());
		if (existente.isPresent()) {
			return existente.get();
		}

		if (donante.getNombre() == null || donante.getNombre().isBlank()
				|| donante.getApellido() == null || donante.getApellido().isBlank()
				|| donante.getCorreo() == null || donante.getCorreo().isBlank()
				|| donante.getTelefono() == null || donante.getTelefono().isBlank()) {
			throw new IllegalArgumentException(
				"Si el donante no existe, debes enviar rutDonante, nombre, apellido, correo y telefono");
		}

		return donanteRepository.save(donante);
	}
}
