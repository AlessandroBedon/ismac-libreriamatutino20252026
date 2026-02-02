package com.distribuida.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "factura_detalle")
public class FacturaDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura_detalle")
    private int idFacturaDetalle;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "subtotal", precision = 10, scale = 2)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "id_factura")

    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "id_libro")

    private Libro libro;

    // 🔹 CONSTRUCTOR VACÍO CORRECTO
    public FacturaDetalle() {
    }

    // getters y setters
    public int getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(int idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
