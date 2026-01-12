package com.distribuida.service;

import com.distribuida.dao.LibroDAO;
import com.distribuida.model.Cliente;
import com.distribuida.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService{

    @Autowired
    private LibroDAO libroDAO;

    @Override
    public List<Libro> findAll() {
        return libroDAO.findAll();
    }

    @Override
    public Optional<Libro> findOne(int id) {
        return libroDAO.findById(id);
    }

    @Override
    public Libro save(Libro libro) {
        return libroDAO.save(libro);
    }

    @Override
    public Libro update(int id, Libro libro) {
        Optional<Libro> libroExistente = libroDAO.findById(id);

        if (libroExistente == null) {
            return null;
        }
        libroExistente.orElse(null).setTitulo(libro.getTitulo());
        libroExistente.orElse(null).setEditorial(libro.getEditorial());
        libroExistente.orElse(null).setNumpaginas(libro.getNumpaginas());
        libroExistente.orElse(null).setEdicion(libro.getEdicion());
        libroExistente.orElse(null).setIdioma(libro.getIdioma());
        libroExistente.orElse(null).setFechapublicacion(libro.getFechapublicacion());
        libroExistente.orElse(null).setDescripcion(libro.getDescripcion());
        libroExistente.orElse(null).setTipopasta(libro.getTipopasta());
        libroExistente.orElse(null).setIsbn(libro.getIsbn());
        libroExistente.orElse(null).setNumejemplares(libro.getNumejemplares());
        libroExistente.orElse(null).setPortada(libro.getPortada());
        libroExistente.orElse(null).setPresentacion(libro.getPresentacion());
        libroExistente.orElse(null).setPrecio(libro.getPrecio());

        return libroDAO.save(libroExistente.orElse(null));
    }

    @Override
    public void delete(int id) {
        if (libroDAO.existsById(id)) {
            libroDAO.deleteById(id);
        }
    }
}