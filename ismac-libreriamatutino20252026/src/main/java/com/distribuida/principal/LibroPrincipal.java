package com.distribuida.principal;

import com.distribuida.model.Autor;
import com.distribuida.model.Categoria;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;

import java.util.Date;

public class LibroPrincipal {
    public static void  main(String[] args){

        Libro libro = new Libro();
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

        //Categoria
        Categoria categoria = new Categoria(2,"Romatico", "Buen libro");
        //Autor
        Autor autor = new Autor(1, "Susana", "Horia", "Perú", "Av. Tierra a la vista", "0998645012", "susana@cienaños.com");


        System.out.println(libro.toString());
        System.out.println(categoria.toString());
        System.out.println(autor.toString());
    }


}
