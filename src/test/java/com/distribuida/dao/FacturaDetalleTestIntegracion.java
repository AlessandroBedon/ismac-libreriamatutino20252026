package com.distribuida.dao;

import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class FacturaDetalleTestIntegracion {

    @Autowired
    public FacturaDetalleDAO facturaDetalleDAO;

    @Autowired
    public FacturaDAO facturaDAO;

    @Autowired
    public LibroDAO libroDAO;

    @Test
    public void testFacturaDetalleFindAll(){
        List<FacturaDetalle> facturaDetalles = facturaDetalleDAO.findAll();
        assertNotNull(facturaDetalles);
        assertTrue(facturaDetalles.size() > 0);
        for (FacturaDetalle item: facturaDetalles){
            System.out.println(item.toString());
        }
    }

    @Test
    public void testFacturaDetalleFindOne(){
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleDAO.findById(1);

        assertTrue(facturaDetalle.isPresent());
        assertEquals(1, facturaDetalle.orElse(null).getIdFacturaDetalle());
        assertEquals(2, facturaDetalle.orElse(null).getCantidad());
        assertEquals(new BigDecimal("30.84"), facturaDetalle.orElse(null).getSubtotal());

        System.out.println(facturaDetalle.toString());
    }

    @Test
    public void testFacturaDetalleSave(){
        Optional<Factura> factura = facturaDAO.findById(1);
        assertTrue(factura.isPresent());

        Optional<Libro> libros = libroDAO.findById(1);
        assertTrue(libros.isPresent());

        FacturaDetalle facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(0);
        facturaDetalle.setCantidad(5);
        facturaDetalle.setSubtotal(new BigDecimal("58.8"));
        facturaDetalle.setFactura(factura.orElse(null));
        facturaDetalle.setLibro(libros.orElse(null));

        FacturaDetalle facturaDetalleGuardado = facturaDetalleDAO.save(facturaDetalle);

        assertNotNull(facturaDetalleGuardado);
        assertEquals(5, facturaDetalleGuardado.getCantidad());
        assertEquals(new BigDecimal("58.8"), facturaDetalleGuardado.getSubtotal());
        assertEquals("FAC-0001", facturaDetalleGuardado.getFactura().getNumFactura());
        assertEquals("Spring in Action", facturaDetalleGuardado.getLibro().getTitulo());
    }

    @Test
    public void testFacturaDetalleUpdate(){
        Optional<FacturaDetalle> facturaDetalle = facturaDetalleDAO.findById(210);
        assertTrue(facturaDetalle.isPresent());

        facturaDetalle.orElse(null).setCantidad(10);
        facturaDetalle.orElse(null).setSubtotal(new BigDecimal("154.5"));

        FacturaDetalle facturaDetalleActualizado = facturaDetalleDAO.save(facturaDetalle.orElse(null));

        assertNotNull(facturaDetalleActualizado);
        assertEquals(10, facturaDetalleActualizado.getCantidad());
        assertEquals(new BigDecimal("154.5"), facturaDetalleActualizado.getSubtotal());

        assertNotNull(facturaDetalleActualizado.getFactura());
        assertNotNull(facturaDetalleActualizado.getLibro());
        assertEquals("FAC-0001", facturaDetalleActualizado.getFactura().getNumFactura());
        assertEquals("Spring in Action", facturaDetalleActualizado.getLibro().getTitulo());
    }

    @Test
    public void testFacturaDetalleDelete(){
        if (facturaDetalleDAO.existsById(210)){
            facturaDetalleDAO.deleteById(210);
        }
        assertFalse(facturaDetalleDAO.existsById(210),"El dato fue eliminado");
    }
}
