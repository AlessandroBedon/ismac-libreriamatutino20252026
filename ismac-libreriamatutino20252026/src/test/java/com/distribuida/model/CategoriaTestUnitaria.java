package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaTestUnitaria {
    private Categoria categoria;

    @BeforeEach
    public void setup(){
        categoria = new Categoria(2,
                "Romantico",
                "Buen libro");
    }

    @Test
        public void testCategoriaConstructor(){

            assertAll("Validar datos de categoria con constructor",
                    () -> assertEquals(2,categoria.getIdCategoria()),
                    () -> assertEquals("Romantico", categoria.getCategoria()),
                    () -> assertEquals("Buen libro", categoria.getDescripcion())
            );
    }

    @Test
    public void testCategoriaSetters(){
        categoria.setIdCategoria(2);
        categoria.setCategoria("Romantico");
        categoria.setDescripcion("Buen libro");



        assertAll("Validar datos de categoria con setters",
                    () -> assertEquals(2, categoria.getIdCategoria()),
                    () -> assertEquals("Romantico", categoria.getCategoria()),
                    () -> assertEquals("Buen libro", categoria.getDescripcion())
            );
    }

}
