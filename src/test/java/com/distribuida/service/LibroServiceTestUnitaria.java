package com.distribuida.service;

import com.distribuida.dao.LibroDAO;
import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibroServiceTestUnitaria {
    @Mock
    private LibroDAO libroDAO;

    @InjectMocks
    private LibroServiceImpl libroService;

    private Libro libro;
    private Categoria categoria;
    private Autor autor;

    @BeforeEach
    public void setUp() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCategoria("Tecnología");
        categoria.setDescripcion("Libros técnicos");

        autor = new Autor();
        autor.setIdAutor(1);
        autor.setNombre("Alessandro");
        autor.setApellido("Bedón");

        libro = new Libro();
        libro.setLibro(1);
        libro.setTitulo("Spring Boot Básico");
        libro.setEditorial("TechBooks");
        libro.setNumpaginas(350);
        libro.setEdicion("Primera");
        libro.setIdioma("Español");
        libro.setFechapublicacion(new Date());
        libro.setDescripcion("Libro introductorio de Spring Boot");
        libro.setTipopasta("Dura");
        libro.setIsbn("978-9999999999");
        libro.setNumejemplares(10);
        libro.setPortada("portada.jpg");
        libro.setPresentacion("Física");
        libro.setPrecio(29.99);
        libro.setCategoria(categoria);
        libro.setAutor(autor);
    }

    @Test
    public void findAll() {
        when(libroDAO.findAll()).thenReturn(List.of(libro));

        List<Libro> libros = libroService.findAll();

        assertNotNull(libros);
        assertEquals(1, libros.size());
        verify(libroDAO, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindOneExistente() {
        when(libroDAO.findById(1)).thenReturn(Optional.of(libro));

        Optional<Libro> resultado = libroService.findOne(1);

        assertNotNull(resultado);
        assertEquals("Spring Boot Básico", resultado.orElse(null).getTitulo());
    }

    @Test
    public void testFindOneNoExistente() {
        when(libroDAO.findById(2)).thenReturn(null);

        Optional<Libro> resultado = libroService.findOne(2);

        assertNull(resultado);
    }

    @Test
    public void testSave() {
        when(libroDAO.save(libro)).thenReturn(libro);

        Libro libroGuardado = libroService.save(libro);

        assertNotNull(libroGuardado);
        assertEquals("Spring Boot Básico", libroGuardado.getTitulo());
    }

    @Test
    public void testUpdateExistente() {
        Libro libroActualizado = new Libro();
        libroActualizado.setTitulo("Spring Boot Avanzado");
        libroActualizado.setEditorial("TechBooks Pro");
        libroActualizado.setNumpaginas(500);
        libroActualizado.setEdicion("Segunda");
        libroActualizado.setIdioma("Español");
        libroActualizado.setDescripcion("Libro avanzado de Spring Boot");
        libroActualizado.setTipopasta("Dura");
        libroActualizado.setPrecio(39.99);

        when(libroDAO.findById(1)).thenReturn(Optional.of(libro));
        when(libroDAO.save(any())).thenReturn(libroActualizado);

        Libro resultado = libroService.update(1, libroActualizado);

        assertNotNull(resultado);
        assertEquals("Spring Boot Avanzado", resultado.getTitulo());
        verify(libroDAO, Mockito.times(1)).save(libro);
    }

    @Test
    public void testUpdateNoExistente() {
        Libro libroNuevo = new Libro();
        when(libroDAO.findById(999)).thenReturn(null);

        Libro resultado = libroService.update(999, libroNuevo);

        assertNull(resultado);
        verify(libroDAO, never()).save(any());
    }

    @Test
    public void testDeleteExistente() {
        when(libroDAO.existsById(1)).thenReturn(true);

        libroService.delete(1);

        verify(libroDAO).deleteById(1);
    }

    @Test
    public void testDeleteNoExistente() {
        when(libroDAO.existsById(999)).thenReturn(false);

        libroService.delete(999);

        verify(libroDAO, never()).deleteById(anyInt());
    }

}
