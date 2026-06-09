package com.fundacionanimal.ms_apadrinamientos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fundacionanimal.ms_apadrinamientos.model.Apadrinamiento;
import com.fundacionanimal.ms_apadrinamientos.repository.ApadrinamientoRepository;

@Service
public class ApadrinamientoService {

	private final ApadrinamientoRepository apadrinamientoRepository;

	public ApadrinamientoService(ApadrinamientoRepository apadrinamientoRepository) {
		this.apadrinamientoRepository = apadrinamientoRepository;
	}

	public Apadrinamiento crearApadrinamiento(Apadrinamiento apadrinamiento) {
		if (apadrinamiento.getFechaInicio() == null) {
			apadrinamiento.setFechaInicio(LocalDate.now());
		}
		if (apadrinamiento.getEstado() == null || apadrinamiento.getEstado().isBlank()) {
			apadrinamiento.setEstado("ACTIVO");
		}

		String estadoNormalizado = apadrinamiento.getEstado().trim().toUpperCase();
		apadrinamiento.setEstado(estadoNormalizado);

		return apadrinamientoRepository.save(apadrinamiento);
	}

	public List<Apadrinamiento> buscarActivosPorRutPadrino(String rutPadrino) {
		return apadrinamientoRepository.findByFkRutPadrinoAndEstado(rutPadrino, "ACTIVO");
	}
}
