package com.fundacionanimal.donacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DONANTE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donante {

	@Id
	@Column(name = "rut", length = 12)
	private String rutDonante;

	@JsonIgnore
	@Column(name = "rut_donante", nullable = false, length = 12)
	private String rutDonanteLegacy;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 100)
	private String apellido;

	@Column(name = "correo", nullable = false, length = 120)
	private String correo;

	@Column(name = "telefono", nullable = false, length = 20)
	private String telefono;

	@PrePersist
	@PreUpdate
	private void sincronizarRut() {
		this.rutDonanteLegacy = this.rutDonante;
	}
}
