package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTestUnitaria {

    private Cliente cliente;

    @BeforeEach
    public void setup(){
        cliente = new Cliente(1
                ,"1750904135"
                ,"Lil"
                ,"Moni"
                ,"Av. Mucha calle"
                ,"099863154"
                ,"lilmoni@bitcoin.com");
    }

@Test
    public void testClienteConstructor(){

        assertAll("Validar datos del Cliente con constructor",
                () -> assertEquals(1, cliente.getIdCliente()),
                () -> assertEquals("1750904135", cliente.getCedula()),
                () -> assertEquals("Lil", cliente.getNombre()),
                () -> assertEquals("Moni", cliente.getApellido()),
                () -> assertEquals("Av. Mucha calle", cliente.getDireccion()),
                () -> assertEquals("099863154", cliente.getTelefono()),
                () -> assertEquals("lilmoni@bitcoin.com", cliente.getCorreo())
                );
    }


    @Test
    public void testClienteSetters(){
        cliente.setIdCliente(2);
        cliente.setCedula("1750904137");
        cliente.setNombre("Maria");
        cliente.setApellido("Angúla");
        cliente.setDireccion("Av. Llorona");
        cliente.setCorreo("mariangula@cry.com");

        assertAll("Validar datos del cliente con setters",
                () -> assertEquals(2, cliente.getIdCliente()),
                () -> assertEquals("1750904137", cliente.getCedula()),
                () -> assertEquals("Maria", cliente.getNombre()),
                () -> assertEquals("Angúla", cliente.getApellido()),
                () -> assertEquals("Av. Llorona", cliente.getDireccion()),
                () -> assertEquals("mariangula@cry.com", cliente.getCorreo())
                );
    }

    @Test
    public void testClienteToString (){

        String str = cliente.toString();

        assertAll("Validar datos del cliente en toString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("1750904135")),
                () -> assertTrue(str.contains("Lil")),
                () -> assertTrue(str.contains("Moni")),
                () -> assertTrue(str.contains("Av. Mucha calle")),
                () -> assertTrue(str.contains("099863154")),
                    () -> assertTrue(str.contains("lilmoni@bitcoin.com"))
                );
    }





}
