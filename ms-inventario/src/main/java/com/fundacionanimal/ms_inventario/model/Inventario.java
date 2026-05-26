package com.fundacionanimal.ms_inventario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "INVENTARIO")
public class Inventario 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insumo")
    private Long idInsumo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo_insumo", nullable = false, length = 50)
    private String tipoInsumo; // Alimento, Medicamento, etc.

    @Column(name = "stock_actual", nullable = false)
    private Double stockActual;

    @Column(name = "stock_minimo", nullable = false)
    private Double stockMinimo;

    @Column(name = "stock_critico", nullable = false)
    private Double stockCritico;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida; // kg, unidades, etc.

    // Constructores
    public Inventario() {}

    public Inventario(String nombre, String tipoInsumo, Double stockActual, Double stockMinimo, Double stockCritico, String unidadMedida) {
        this.nombre = nombre;
        this.tipoInsumo = tipoInsumo;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.stockCritico = stockCritico;
        this.unidadMedida = unidadMedida;
    }

    // Getters y Setters
    public Long getIdInsumo() { return idInsumo; }
    public void setIdInsumo(Long idInsumo) { this.idInsumo = idInsumo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipoInsumo() { return tipoInsumo; }
    public void setTipoInsumo(String tipoInsumo) { this.tipoInsumo = tipoInsumo; }

    public Double getStockActual() { return stockActual; }
    public void setStockActual(Double stockActual) { this.stockActual = stockActual; }

    public Double getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Double stockMinimo) { this.stockMinimo = stockMinimo; }

    public Double getStockCritico() { return stockCritico; }
    public void setStockCritico(Double stockCritico) { this.stockCritico = stockCritico; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }
}
