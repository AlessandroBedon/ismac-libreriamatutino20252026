package com.distribuida.dao;

import com.distribuida.model.Autor;
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

public class AutorTestIntegracion {
    @Autowired
    public AutorDAO autorDAO;

    @Test
    public void testAutorfindAll(){
        List<Autor> autores = autorDAO.findAll();
        assertNotNull(autores);
        assertTrue(autores.size() > 0);
        for (Autor item: autores) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void testAutorfinOne(){
        Optional<Autor> autor = autorDAO.findById(1);
        assertTrue(autor.isPresent(),"El autor con ID=1 si existe");
        System.out.println(autor.toString());
    }

    @Test
    public void testAutorSave(){
        Autor autor = new Autor(0,"Diego","Bedon","Ecuador","Calle C.34","0998631030","dbedon@correo.com");
        Autor autorGuardado = autorDAO.save(autor);
        assertNotNull(autorGuardado,"El autor se guardó correctamente");
        assertEquals("Diego", autorGuardado.getNombre());
        assertEquals("Bedon", autorGuardado.getApellido());
    }

    @Test
    public void testAutorUpdate(){
        Optional<Autor> autor = autorDAO.findById(60);

        autor.orElse(null).setPais("Argentina");
        autor.orElse(null).setDireccion("Calle 13");
        autor.orElse(null).setCorreo("13@correo.com");

        Autor autorActualizado = autorDAO.save(autor.orElse(null));
        assertNotNull(autorActualizado,"El autor fue actualizado");
        assertEquals("Argentina", autorActualizado.getPais());
        assertEquals("Calle 13", autorActualizado.getDireccion());
    }

    @Test
    public void testAutorDelete(){
        if (autorDAO.existsById(60)) {
            autorDAO.deleteById(60);
        }
        assertFalse(autorDAO.existsById(60),"El autor fue eliminado");
    }

}
