package com.fundacionanimal.ms_apadrinamientos.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "APADRINAMIENTO")
public class Apadrinamiento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_apadrinamiento")
	private Long idApadrinamiento;

	@Column(name = "id_animal", nullable = false)
	private Long idAnimal;

	@Column(name = "fk_rut_padrino", nullable = false, length = 12)
	private String fkRutPadrino;

	@Column(name = "monto_mensual", nullable = false)
	private Double montoMensual;

	@Column(name = "fecha_inicio", nullable = false)
	private LocalDate fechaInicio;

	@Column(name = "estado", nullable = false, length = 20)
	private String estado;

	public Apadrinamiento() {
	}

	public Apadrinamiento(Long idAnimal, String fkRutPadrino, Double montoMensual, LocalDate fechaInicio, String estado) {
		this.idAnimal = idAnimal;
		this.fkRutPadrino = fkRutPadrino;
		this.montoMensual = montoMensual;
		this.fechaInicio = fechaInicio;
		this.estado = estado;
	}

	public Long getIdApadrinamiento() {
		return idApadrinamiento;
	}

	public void setIdApadrinamiento(Long idApadrinamiento) {
		this.idApadrinamiento = idApadrinamiento;
	}

	public Long getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(Long idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getFkRutPadrino() {
		return fkRutPadrino;
	}

	public void setFkRutPadrino(String fkRutPadrino) {
		this.fkRutPadrino = fkRutPadrino;
	}

	public Double getMontoMensual() {
		return montoMensual;
	}

	public void setMontoMensual(Double montoMensual) {
		this.montoMensual = montoMensual;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
