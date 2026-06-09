package com.fundacionanimal.ms_alertas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fundacionanimal.ms_alertas.model.Alerta;
import com.fundacionanimal.ms_alertas.repository.AlertaRepository;

@Service
public class AlertaService {

	private final AlertaRepository alertaRepository;

	public AlertaService(AlertaRepository alertaRepository) {
		this.alertaRepository = alertaRepository;
	}

	public Alerta crearAlerta(Alerta alerta) {
		if (alerta.getFechaGeneracion() == null) {
			alerta.setFechaGeneracion(LocalDateTime.now());
		}
		if (alerta.getLeida() == null) {
			alerta.setLeida(false);
		}
		return alertaRepository.save(alerta);
	}

	public List<Alerta> listarAlertasNoLeidas() {
		return alertaRepository.findByLeidaFalse();
	}
}
