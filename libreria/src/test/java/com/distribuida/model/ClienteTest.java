package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

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
}
