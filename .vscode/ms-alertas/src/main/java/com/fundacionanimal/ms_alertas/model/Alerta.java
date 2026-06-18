package com.fundacionanimal.ms_alertas.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ALERTA")
public class Alerta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_alerta")
	private Long idAlerta;

	@Column(name = "tipo_alerta", nullable = false, length = 50)
	private String tipoAlerta;

	@Column(name = "mensaje", nullable = false, length = 255)
	private String mensaje;

	@Column(name = "fecha_generacion", nullable = false)
	private LocalDateTime fechaGeneracion;

	@Column(name = "leida", nullable = false)
	private Boolean leida;

	@Column(name = "id_referencia", nullable = false)
	private Long idReferencia;

	public Alerta() {
	}

	public Alerta(String tipoAlerta, String mensaje, LocalDateTime fechaGeneracion, Boolean leida, Long idReferencia) {
		this.tipoAlerta = tipoAlerta;
		this.mensaje = mensaje;
		this.fechaGeneracion = fechaGeneracion;
		this.leida = leida;
		this.idReferencia = idReferencia;
	}

	public Long getIdAlerta() {
		return idAlerta;
	}

	public void setIdAlerta(Long idAlerta) {
		this.idAlerta = idAlerta;
	}

	public String getTipoAlerta() {
		return tipoAlerta;
	}

	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDateTime getFechaGeneracion() {
		return fechaGeneracion;
	}

	public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
		this.fechaGeneracion = fechaGeneracion;
	}

	public Boolean getLeida() {
		return leida;
	}

	public void setLeida(Boolean leida) {
		this.leida = leida;
	}

	public Long getIdReferencia() {
		return idReferencia;
	}

	public void setIdReferencia(Long idReferencia) {
		this.idReferencia = idReferencia;
	}
}
