package com.distribuida.dao;

import com.distribuida.model.Categoria;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)

public class CategoriaTestIntegracion {
    @Autowired
    public CategoriaDAO categoriaDAO;

    @Test
    public void findAll(){
        List<Categoria> categorias = categoriaDAO.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size() > 0);
        for (Categoria item: categorias){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Categoria> categoria = categoriaDAO.findById(1);
        assertTrue(categoria.isPresent(),"La categoria con ID=1 si existe");
        System.out.println(categoria.toString());
    }

    @Test
    public void save(){
        Categoria categoria = new Categoria(0,"Horror","Terriblemente bueno");
        Categoria categoraGuardado = categoriaDAO.save(categoria);
        assertNotNull(categoraGuardado,"La categoria se guardo correctamente");
        assertEquals("Horror", categoraGuardado.getCategoria());
        assertEquals("Terriblemente bueno", categoraGuardado.getDescripcion());
    }

    @Test
    public void update(){
        Optional<Categoria> categoria = categoriaDAO.findById(59);

        categoria.orElse(null).setCategoria("Fantasia");
        categoria.orElse(null).setDescripcion("Buena obra");
    }

    @Test
    public void delete(){
        if (categoriaDAO.existsById(59)){
            categoriaDAO.deleteById(59);
        }
        assertFalse(categoriaDAO.existsById(59),"El dato fue eliminado");
    }

}
