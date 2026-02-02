package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FacturaTestUnitaria {

    private Factura factura;
    private Cliente cliente;

    @BeforeEach
    public void setup() {
        cliente = new Cliente(1
                , "1750904135"
                , "Lil"
                , "Moni"
                , "Av. Mucha calle"
                , "099863154"
                , "lilmoni@bitcoin.com");

        factura = new Factura();

        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setTotalNeto(100.00);
        factura.setIva(15.00);
        factura.setTotal(115.00);
        //Inyeccion de dependencias
        factura.setCliente(cliente);
    }

    @Test
    public void testFacturaConstructor() {

        assertAll("Validar datos constructor Factura",
                () -> assertEquals(1, factura.getIdFactura()),
                () -> assertEquals("FAC-0001", factura.getNumFactura()),
                //() -> assertEquals(new Date(),factura.getFecha()),
                () -> assertEquals(100.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(115.00, factura.getTotal())

        );
    }

    @Test
    public void testFacturaSetters() {
        cliente = new Cliente(2, "1750904885", "Lil2", "Moni2", "Dire2", "0998631025", "moni@bitcoin.com");
        factura = new Factura();

        factura.setIdFactura(2);
        factura.setNumFactura("FAC-0002");
        factura.setFecha(new Date());
        factura.setTotalNeto(200.00);
        factura.setIva(15.00);
        factura.setTotal(215.00);
        //test de inyeccion de dependencias
        factura.setCliente(cliente);

        assertAll("Validar metodos setters-Factura",
                () -> assertEquals(2, factura.getIdFactura()),
                //() -> assertEquals(new Date(), factura.getFecha()),
                () -> assertEquals(200.00, factura.getTotalNeto()),
                () -> assertEquals(15.00, factura.getIva()),
                () -> assertEquals(215.00, factura.getTotal()),
                () -> assertEquals("1750904885", factura.getCliente().getCedula())
        );


    }

    @Test
    public void testFacturatoString(){
        String str = factura.toString();

        assertAll("Validas datos de toString - Factura",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("FAC-0001")),
                () -> assertTrue(str.contains("100.0")),
                () -> assertTrue(str.contains("15.0")),
                () -> assertTrue(str.contains("115.0")),
                () -> assertTrue(str.contains("Lil"))
                );
    }

    @Test
public void testFacturaInyeccion(){
        assertAll("Validar metodo de inyeccion",
                () -> assertNotNull(factura.getCliente()),
                () -> assertEquals("Moni", factura.getCliente().getApellido())
                );
    }

    @Test
public void testFacturaValoresNegativos(){

        factura.setTotal(-100.00);
        assertAll("Validar datos negativos - factura",
                () -> assertEquals(-100.00, factura.getTotal())
        );
        // Validar que se evite valores negativos.
    }
}