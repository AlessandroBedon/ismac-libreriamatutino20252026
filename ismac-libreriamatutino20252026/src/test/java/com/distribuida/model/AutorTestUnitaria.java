package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutorTestUnitaria {
    private Autor autor;

    @BeforeEach
    public void setup(){
        autor = new Autor(1
                ,"Susana"
                ,"Horia"
                ,"Perú"
                ,"Av. Tierra a la vista"
                ,"0998645012"
                ,"susana@cienaños.com");
    }

    @Test
        public void testAutorConstructor(){

            assertAll("Validar datos del autor con constructor",
                    () -> assertEquals(1, autor.getIdAutor()),
                    () -> assertEquals("Susana", autor.getNombre()),
                    () -> assertEquals("Horia", autor.getApellido()),
                    () -> assertEquals("Av. Tierra a la vista", autor.getDireccion()),
                    () -> assertEquals("0998645012", autor.getTelefono()),
                    () -> assertEquals("susana@cienaños.com", autor.getCorreo())
            );
    }

    @Test
    public void testAutorSetters(){
        autor.setIdAutor(2);
        autor.setNombre("Susana");
        autor.setApellido("Horia");
        autor.setPais("Perú");
        autor.setDireccion("Av. Tierra a la vista");
        autor.setTelefono("099696969");
        autor.setCorreo("susana@cienaños.com");

        assertAll("Validar datos del autor con setters",
                () -> assertEquals(2, autor.getIdAutor()),
                () -> assertEquals("Susana", autor.getNombre()),
                () -> assertEquals("Horia", autor.getApellido()),
                () -> assertEquals("Perú", autor.getPais()),
                () -> assertEquals("Av. Tierra a la vista", autor.getDireccion()),
                () -> assertEquals("099696969", autor.getTelefono()),
                () -> assertEquals("susana@cienaños.com", autor.getCorreo())
        );
    }

}
