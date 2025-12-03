package com.distribuida.principal;

import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;

import java.math.BigDecimal;
import java.util.Date;

public class FacturaDetallePrincipal {
    public static void main(String[] args) {

        //Factura
        Factura factura = new Factura();

        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(50.00);
        factura.setIva(15.00);
        factura.setTotal(65.00);
        //FacturaDetalle
        FacturaDetalle facturaDetalle = new FacturaDetalle();

        facturaDetalle.setIdFacturaDetalle(2);
        facturaDetalle.setCantidad(25);
        facturaDetalle.setSubtotal(new BigDecimal("58.8"));

        //Libro
        Libro libro = new Libro();
        libro.setLibro(2);
        libro.setTitulo("Cien años de soledad");
        libro.setEditorial("Editorial Sudamericana");
        libro.setNumpaginas(471);
        libro.setEdicion("Primera edición");
        libro.setIdioma("Español");
        libro.setFechapublicacion(new Date( ));
        libro.setDescripcion("Una obra maestra de Gabriel García Márquez que narra la historia de la familia Buendía en el mítico pueblo de Macondo.");
        libro.setTipopasta("Pasta dura");
        libro.setIsbn("978-84-376-0494-7");
        libro.setNumejemplares(25);
        libro.setPortada("https://example.com/portadas/cien-anos-de-soledad.jpg");
        libro.setPresentacion("Libro físico");
        libro.setPrecio(349.99);

        System.out.println(libro.toString());
        System.out.println(factura.toString());
    }


}
