package com.fundacionanimal.donacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Column(name = "rut_donante", length = 12)
	private String rutDonante;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "apellido", nullable = false, length = 100)
	private String apellido;

	@Column(name = "correo", nullable = false, length = 120)
	private String correo;

	@Column(name = "telefono", nullable = false, length = 20)
	private String telefono;
}
