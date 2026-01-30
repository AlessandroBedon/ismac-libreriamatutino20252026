package com.distribuida.model;

public class FacturaDetalle {

    private int idFacturaDetalle;
    private int cantidad;
    private Double subtotal;

    //Inyecciones
    private Factura factura;
    private Libro libro;

    public FacturaDetalle() {
        this.idFacturaDetalle = idFacturaDetalle;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.factura = factura;
        this.libro = libro;
    }

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

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
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

    public void setFactura(Cliente cliente) {
        this.factura = factura;
    }

    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "idFacturaDetalle=" + idFacturaDetalle +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", factura=" + factura +
                ", libro=" + libro +
                '}';
    }
}