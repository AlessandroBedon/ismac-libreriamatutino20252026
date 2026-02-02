package com.distribuida.dao;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class LibroTestIntegracion {

    @Autowired
    public LibroDAO libroDAO;

    @Autowired
    public CategoriaDAO categoriaDAO;

    @Autowired
    public AutorDAO autorDAO;

    @Test
    public void testLibroFindAll(){
        List<Libro> libros = libroDAO.findAll();
        assertNotNull(libros);
        assertTrue(libros.size() > 0);
        for (Libro item: libros){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Libro> libro = libroDAO.findById(1);
        assertTrue(libro.isPresent(),"El libro si existe");
        System.out.println(libro.toString());
    }

    @Test
    public void save(){
        Optional<Autor> autor = autorDAO.findById(1);
        Optional<Categoria> categoria = categoriaDAO.findById(1);

        assertTrue(autor.isPresent());
        assertTrue(categoria.isPresent());

        Libro libro = new Libro();
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

        libro.setAutor(autor.orElse(null));
        libro.setCategoria(categoria.orElse(null));

        Libro libroGuardado = libroDAO.save(libro);

        assertNotNull(libroGuardado,"El libro se guardo correctamente");
        assertEquals("Editorial Sudamericana",libroGuardado.getEditorial());
        assertEquals("978-84-376-0494-7",libroGuardado.getIsbn());
        assertEquals("https://example.com/portadas/cien-anos-de-soledad.jpg",libroGuardado.getPortada());
        assertEquals("Primera edicion",libroGuardado.getEdicion());
    }

    @Test
    public void testLibroUpdate(){
        Optional<Autor> autor = autorDAO.findById(1);
        Optional<Categoria> categoria = categoriaDAO.findById(1);
        Optional<Libro> libro = libroDAO.findById(80);

        assertTrue(autor.isPresent());
        assertTrue(categoria.isPresent());
        assertTrue(libro.isPresent());

        libro.orElse(null).setTitulo("Harry Potter y la piedra filosofal");
        libro.orElse(null).setEditorial("Bloomsbury Publishing");
        libro.orElse(null).setNumpaginas(223);
        libro.orElse(null).setEdicion("Primera edición");
        libro.orElse(null).setIdioma("Inglés");
        libro.orElse(null).setFechapublicacion(new Date());
        libro.orElse(null).setDescripcion("La historia del joven mago Harry Potter en su primer año en Hogwarts.");
        libro.orElse(null).setTipopasta("Pasta dura");
        libro.orElse(null).setIsbn("978-0-7475-3269-9");
        libro.orElse(null).setNumejemplares(30);
        libro.orElse(null).setPortada("https://example.com/portadas/harry-potter-1.jpg");
        libro.orElse(null).setPresentacion("Libro físico");
        libro.orElse(null).setPrecio(199.99);

        libro.orElse(null).setAutor(autor.orElse(null));
        libro.orElse(null).setCategoria(categoria.orElse(null));

        Libro libroActualizado = libroDAO.save(libro.orElse(null));

        assertNotNull(libroActualizado);
        assertEquals("Harry Potter y la piedra filosofal", libroActualizado.getTitulo());
        assertEquals("Bloomsbury Publishing", libroActualizado.getEditorial());
        assertEquals("Primera edición", libroActualizado.getEdicion());
        assertEquals("Inglés", libroActualizado.getIdioma());
        assertEquals("Pamela", libroActualizado.getAutor().getNombre());
    }

    @Test
    public void delete(){
        if (libroDAO.existsById(80)){
            libroDAO.deleteById(80);
        }
        assertFalse(libroDAO.existsById(80),"El dato fue eliminado");
    }

}
