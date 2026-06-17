package com.fundacionanimal.donacion.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DONACION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Donacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_don")
	private Long idDon;

	@Column(name = "monto", nullable = false)
	private Double monto;

	@Column(name = "fecha", nullable = false)
	private LocalDate fecha;

	@Column(name = "estado", nullable = false, length = 50)
	private String estado;

	@ManyToOne
	@JoinColumn(name = "fk_rut", referencedColumnName = "rut", nullable = false)
	private Donante donante;
}
