package com.distribuida.controller;

import com.distribuida.model.Categoria;
import com.distribuida.model.Libro;
import com.distribuida.service.CategoriaService;
import com.distribuida.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> findAll() {
        List<Libro> libros = libroService.findAll();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> findOne(@PathVariable int id){
        Optional<Libro> libro = libroService.findOne(id);
        if (libro == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro.orElse(null));
    }

    @PostMapping
    public ResponseEntity<Libro> save (@RequestBody Libro libro){
        Libro nuevoLibro = libroService.save(libro);
        return ResponseEntity.ok(nuevoLibro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> update (@PathVariable int id, @RequestBody Libro libro){
        Libro libroActulizado = libroService.update(id, libro);
        if (libroActulizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libroActulizado);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable int id){
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
