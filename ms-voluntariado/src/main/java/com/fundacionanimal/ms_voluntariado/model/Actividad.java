package com.fundacionanimal.ms_voluntariado.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_actividad")
	private Long idActividad;

	@Column(name = "nombre_actividad", nullable = false, length = 100)
	private String nombreActividad;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	@Column(name = "cupos_disponibles", nullable = false)
	private Integer cuposDisponibles;

	public Actividad() {
	}

	public Actividad(String nombreActividad, LocalDateTime fecha, Integer cuposDisponibles) {
		this.nombreActividad = nombreActividad;
		this.fecha = fecha;
		this.cuposDisponibles = cuposDisponibles;
	}

	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Integer getCuposDisponibles() {
		return cuposDisponibles;
	}

	public void setCuposDisponibles(Integer cuposDisponibles) {
		this.cuposDisponibles = cuposDisponibles;
	}
}
