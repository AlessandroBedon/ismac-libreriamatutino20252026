package com.distribuida.service;

import com.distribuida.dao.AutorDAO;
import com.distribuida.model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {
    @Autowired
    private AutorDAO autorDAO;

    @Override
    public List<Autor> findAll() {
        return autorDAO.findAll();
    }

    @Override
    public Optional<Autor> findOne(int id) {
        return autorDAO.findById(id);
    }

    @Override
    public Autor save(Autor autor) {
        return autorDAO.save(autor);
    }

    @Override
    public Autor update(int id, Autor autor) {
        Optional<Autor> autorExistente = autorDAO.findById(id);
        if (autorExistente == null){
            return null;
        }
        autorExistente.orElse(null).setIdAutor(autor.getIdAutor());
        autorExistente.orElse(null).setNombre(autor.getNombre());
        autorExistente.orElse(null).setApellido(autor.getApellido());
        autorExistente.orElse(null).setDireccion(autor.getDireccion());
        autorExistente.orElse(null).setTelefono(autor.getTelefono());
        autorExistente.orElse(null).setCorreo(autor.getCorreo());

        return autorDAO.save(autorExistente.orElse(null));
    }

    @Override
    public void delete(int id) {
        if (autorDAO.existsById(id)){
            autorDAO.deleteById(id);
        }
    }
}
