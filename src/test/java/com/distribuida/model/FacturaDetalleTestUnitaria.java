package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaDetalleTestUnitaria {
    private FacturaDetalle facturaDetalle;
    private Factura factura;
    private Libro libro;

    @BeforeEach
    public void setup(){
        facturaDetalle = new FacturaDetalle();

        facturaDetalle.setIdFacturaDetalle(2);
        facturaDetalle.setCantidad(25);
        facturaDetalle.setSubtotal(new BigDecimal("58.8"));

        factura = new Factura();

        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(50.00);
        factura.setIva(15.00);
        factura.setTotal(65.00);

        libro = new Libro();

        libro.setLibro(2);
        libro.setTitulo("Cien años de soledad");
        libro.setEditorial("Editorial Sudamericana");
        libro.setNumpaginas(471);
        libro.setEdicion("Primera edicion");
        libro.setIdioma("Español");
        libro.setFechapublicacion(new Date( ));
        libro.setDescripcion("Una obra maestra de Gabriel García Márquez que narra la historia de la familia Buendía en el mítico pueblo de Macondo.");
        libro.setTipopasta("Pasta dura");
        libro.setIsbn("978-84-376-0494-7");
        libro.setNumejemplares(25);
        libro.setPortada("https://example.com/portadas/cien-anos-de-soledad.jpg");
        libro.setPresentacion("Libro fisico");
        libro.setPrecio(349.99);

        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);
    }

    @Test
    public void testFacturaDetalleConstructor(){

        assertAll("Validar datos factura detalla con constructor",
                () -> assertEquals(2, facturaDetalle.getIdFacturaDetalle()),
                () -> assertEquals(25, facturaDetalle.getCantidad()),
                () -> assertEquals(58.8, facturaDetalle.getSubtotal())
        );
    }

    @Test
    public void testFacturaDetalletoString(){
        String str = facturaDetalle.toString();

        assertAll("Validar datos de toString - Factura detalle",
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("25")),
                () -> assertTrue(str.contains("58.8")),
                            //FACTURA
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-0001")),
                () -> assertTrue(str.contains("50.00")),
                            //LIBRO
                () -> assertTrue(str.contains("Cien años de soledad")),
                () -> assertTrue(str.contains("Editorial Sudamericana")),
                () -> assertTrue(str.contains("Español"))
        );
    }

    @Test
    public void testFacturaDetalleInyeccion(){
        assertAll("Validar metodo inyector",
                () -> assertNotNull(facturaDetalle.getFactura()),
                () -> assertNotNull(facturaDetalle.getLibro()),

                () -> assertEquals("FAC-0001", facturaDetalle.getFactura().getNumFactura()),
                () -> assertEquals(65.00, facturaDetalle.getFactura().getTotal()),

                () -> assertEquals("Cien años de soledad", facturaDetalle.getLibro().getTitulo()),
                () -> assertEquals("Español", facturaDetalle.getLibro().getIdioma())
                );

    }

}


