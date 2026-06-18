package com.fundacionanimal.donacion.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fundacionanimal.donacion.model.Donante;
import com.fundacionanimal.donacion.repository.DonanteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DonanteService {

	private final DonanteRepository donanteRepository;

	public Donante registrarDonante(Donante donante) {
		return donanteRepository.save(donante);
	}

	public Optional<Donante> buscarPorRut(String rutDonante) {
		return donanteRepository.findById(rutDonante);
	}

	public Donante obtenerOCrearDonante(Donante donante) {
		return donanteRepository.findById(donante.getRutDonante())
			.orElseGet(() -> donanteRepository.save(donante));
	}
}
