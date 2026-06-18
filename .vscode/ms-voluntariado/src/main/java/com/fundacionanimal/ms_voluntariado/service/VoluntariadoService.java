package com.fundacionanimal.ms_voluntariado.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundacionanimal.ms_voluntariado.model.Actividad;
import com.fundacionanimal.ms_voluntariado.model.InscripcionVoluntario;
import com.fundacionanimal.ms_voluntariado.repository.ActividadRepository;
import com.fundacionanimal.ms_voluntariado.repository.InscripcionVoluntarioRepository;

@Service
public class VoluntariadoService {

	private final ActividadRepository actividadRepository;
	private final InscripcionVoluntarioRepository inscripcionVoluntarioRepository;

	public VoluntariadoService(ActividadRepository actividadRepository,
			InscripcionVoluntarioRepository inscripcionVoluntarioRepository) {
		this.actividadRepository = actividadRepository;
		this.inscripcionVoluntarioRepository = inscripcionVoluntarioRepository;
	}

	public List<Actividad> listarActividadesDisponibles() {
		return actividadRepository.findByCuposDisponiblesGreaterThan(0);
	}

	@Transactional
	public InscripcionVoluntario inscribirVoluntario(InscripcionVoluntario inscripcion) {
		Optional<Actividad> opcionalActividad = actividadRepository.findById(inscripcion.getIdActividad());
		if (opcionalActividad.isEmpty()) {
			return null;
		}

		Actividad actividad = opcionalActividad.get();
		Integer cupos = actividad.getCuposDisponibles();
		if (cupos == null || cupos <= 0) {
			return null;
		}

		actividad.setCuposDisponibles(cupos - 1);
		actividadRepository.save(actividad);

		if (inscripcion.getAsistenciaConfirmada() == null) {
			inscripcion.setAsistenciaConfirmada(false);
		}

		return inscripcionVoluntarioRepository.save(inscripcion);
	}
}
