package com.fundacionanimal.donacion.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DONACION")
public class Donaciones 
{
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

    @Column(name = "fk_rut", length = 12)
    private String fkRut;

    // Constructores
    public Donaciones() {}

    public Donaciones(Double monto, LocalDate fecha, String estado, String fkRut) {
        this.monto = monto;
        this.fecha = fecha;
        this.estado = estado;
        this.fkRut = fkRut;
    }

    // Getters y Setters
    public Long getIdDon() { return idDon; }
    public void setIdDon(Long idDon) { this.idDon = idDon; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFkRut() { return fkRut; }
    public void setFkRut(String fkRut) { this.fkRut = fkRut; }
}

