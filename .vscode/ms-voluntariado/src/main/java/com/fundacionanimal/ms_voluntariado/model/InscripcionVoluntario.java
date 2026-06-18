package com.fundacionanimal.ms_voluntariado.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "INSCRIPCION_VOLUNTARIO")
public class InscripcionVoluntario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_inscripcion")
	private Long idInscripcion;

	@Column(name = "id_actividad", nullable = false)
	private Long idActividad;

	@Column(name = "rut_voluntario", nullable = false, length = 12)
	private String rutVoluntario;

	@Column(name = "asistencia_confirmada", nullable = false)
	private Boolean asistenciaConfirmada;

	public InscripcionVoluntario() {
	}

	public InscripcionVoluntario(Long idActividad, String rutVoluntario, Boolean asistenciaConfirmada) {
		this.idActividad = idActividad;
		this.rutVoluntario = rutVoluntario;
		this.asistenciaConfirmada = asistenciaConfirmada;
	}

	public Long getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(Long idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	public String getRutVoluntario() {
		return rutVoluntario;
	}

	public void setRutVoluntario(String rutVoluntario) {
		this.rutVoluntario = rutVoluntario;
	}

	public Boolean getAsistenciaConfirmada() {
		return asistenciaConfirmada;
	}

	public void setAsistenciaConfirmada(Boolean asistenciaConfirmada) {
		this.asistenciaConfirmada = asistenciaConfirmada;
	}
}
