package com.distribuida.service;

import com.distribuida.dao.AutorDAO;
import com.distribuida.dao.ClienteDAO;
import com.distribuida.model.Autor;
import com.distribuida.model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTestUnitaria {

    @Mock
    private AutorDAO autorDAO;

    @InjectMocks
    private  AutorServiceImpl autorService;

    private Autor autor;

    @BeforeEach
    public void setUp(){
        autor = new Autor();
        autor.setIdAutor(1);
        autor.setDireccion("La boca");
        autor.setApellido("Bedón");
        autor.setNombre("Alessandro");
        autor.setCorreo("diego@correo.com");
        autor.setTelefono("0998636054");
        autor.setPais("Argentina");
    }

    @Test
    public void findAll(){
        when(autorDAO.findAll()).thenReturn(List.of(autor));
        List<Autor> autores = autorService.findAll();

        assertNotNull(autores);
        assertEquals(1,autores.size());
        verify(autorDAO, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindOneExistente(){
        when(autorDAO.findById(1)).thenReturn(Optional.ofNullable(autor));
        Optional<Autor> autor = autorService.findOne(1);

        assertNotNull(autor);
        assertEquals("Alessandro", autor.orElse(null).getNombre());
    }

    @Test
    public void testFindOneNoExistente(){
        when(autorDAO.findById(2)).thenReturn(null);
        Optional<Autor> autor = autorService.findOne(2);
        assertNull(autor);
    }

    @Test
    public void testSave(){
        when(autorDAO.save(autor)).thenReturn(autor);
        Autor autorGuardado = autorService.save(autor);
        assertNotNull(autorGuardado);
        assertEquals("Alessandro", autorGuardado.getNombre());
    }

    @Test
    public void testUpdateExistente(){
        Autor autorActualizado = new Autor();

        autorActualizado.setNombre("Alessandro2");
        autorActualizado.setApellido("Bedon2");
        autorActualizado.setDireccion("La boca2");
        autorActualizado.setTelefono("09986360542");
        autorActualizado.setCorreo("diego@correo.com2");
        autorActualizado.setPais("Argentina2");

        when(autorDAO.findById(1)).thenReturn(Optional.ofNullable(autor));
        when(autorDAO.save(any())).thenReturn(autorActualizado);

        Autor autorResultado = autorService.update(1, autorActualizado);

        assertNotNull(autorResultado);
        assertEquals("Alessandro2", autorResultado.getNombre());
        verify(autorDAO, Mockito.times(1)).save(autor);
    }

    @Test
    public void testUpdateNoExistente(){
        Autor autorNuevo = new Autor();
        when(autorDAO.findById(999)).thenReturn(null);
        Autor resultado = autorService.update(999, autorNuevo);

        assertNull(resultado);
        verify(autorDAO, never()).save(any());
    }

    @Test
    public void testDeleteExistente(){
        when(autorDAO.existsById(1)).thenReturn(true);
        autorService.delete(1);
        verify(autorDAO).deleteById(1);
    }

    @Test
    public void testDeleteNoExistente(){
        when(autorDAO.existsById(999)).thenReturn(false);
        autorService.delete(999);
        verify(autorDAO, never()).deleteById(anyInt());
    }
}
