package com.distribuida.service;

import com.distribuida.dao.CategoriaDAO;
import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDAO categoriaDAO;
    @Override
    public List<Categoria> findAll() {
        return categoriaDAO.findAll();
    }

    @Override
    public Optional<Categoria> findOne(int id) {
        return categoriaDAO.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaDAO.save(categoria);
    }

    @Override
    public Categoria update(int id, Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaDAO.findById(id);

        if (categoriaExistente == null) {
            return null;
        }
        categoriaExistente.orElse(null).setIdCategoria(categoria.getIdCategoria());
        categoriaExistente.orElse(null).setCategoria(categoria.getCategoria());
        categoriaExistente.orElse(null).setDescripcion(categoria.getDescripcion());

        return categoriaDAO.save(categoriaExistente.orElse(null));
    }

    @Override
    public void delete(int id) {
        if (categoriaDAO.existsById(id)) {
            categoriaDAO.deleteById(id);
        }
    }
}
