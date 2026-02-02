package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTestUnitaria {
    private Categoria categoria;
    private Autor autor;
    private Libro libro;

    @BeforeEach
    public void setup() {

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


        categoria = new Categoria(2,
                "Romantico",
                "Buen libro");

        autor = new Autor(1,
                "Susana",
                "Horia",
                "Perú",
                "Av. Tierra a la vista",
                "0985842125",
                "susana@cienaños.com");

        //Inyecciones de dependencias
        libro.setCategoria(categoria);
        libro.setAutor(autor);
    }

    @Test
    public void testLibroConstructor() {
        assertAll("Validar datos de Libro con setters",

                () -> assertEquals(2, libro.getLibro()),
                () -> assertEquals("Cien años de soledad", libro.getTitulo()),
                () -> assertEquals("Editorial Sudamericana", libro.getEditorial()),
                () -> assertEquals(471, libro.getNumpaginas()),
                () -> assertEquals("Primera edicion", libro.getEdicion()),
                () -> assertEquals("Español", libro.getIdioma()),
                //() -> assertEquals(fecha, libro.getFechaPublicidad()),
                () -> assertEquals("Una obra maestra de Gabriel García Márquez que narra la historia de la familia Buendía en el mítico pueblo de Macondo.", libro.getDescripcion()),
                () -> assertEquals("Pasta dura", libro.getTipopasta()),
                () -> assertEquals("978-84-376-0494-7", libro.getIsbn()),
                () -> assertEquals(25, libro.getNumejemplares()),
                () -> assertEquals("https://example.com/portadas/cien-anos-de-soledad.jpg", libro.getPortada()),
                () -> assertEquals("Libro fisico", libro.getPresentacion()),
                () -> assertEquals(349.99, libro.getPrecio())
        );

    }

    @Test
    public void testLibroToString() {
        String str = libro.toString();

        assertAll("Validar contenido del toString",
                () -> assertTrue(str.contains("Cien años de soledad")),
                () -> assertTrue(str.contains("Editorial Sudamericana")),
                () -> assertTrue(str.contains("471")),
                () -> assertTrue(str.contains("Primera edicion")),
                () -> assertTrue(str.contains("978-84-376-0494-7")),
                () -> assertTrue(str.contains("Romantico")),
                () -> assertTrue(str.contains("Libro fisico"))
        );
    }

    @Test
    public void testLibroInyeccion() {
        assertAll("Validar inyección de Autor y Categoria",
                () -> assertNotNull(libro.getCategoria()),
                () -> assertNotNull(libro.getAutor()),

                () -> assertEquals("Romantico", libro.getCategoria().getCategoria()),
                () -> assertEquals("Susana", libro.getAutor().getNombre())
        );
    }

}
