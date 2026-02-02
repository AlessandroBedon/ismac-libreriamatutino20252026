package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleDAO;
import com.distribuida.model.Factura;
import com.distribuida.model.FacturaDetalle;
import com.distribuida.model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FacturaDetalleTestUnitaria {

    @Mock
    private FacturaDetalleDAO facturaDetalleDAO;

    @InjectMocks
    private FacturaDetalleServiceImpl facturaDetalleService;

    private FacturaDetalle facturaDetalle;
    private Factura factura;
    private Libro libro;

    @BeforeEach
    public void setUp() {
        factura = new Factura();
        factura.setIdFactura(1);

        libro = new Libro();
        libro.setLibro(1);
        libro.setTitulo("Spring Boot Básico");

        facturaDetalle = new FacturaDetalle();
        facturaDetalle.setIdFacturaDetalle(1);
        facturaDetalle.setCantidad(2);
        facturaDetalle.setSubtotal(new BigDecimal("59.98"));
        facturaDetalle.setFactura(factura);
        facturaDetalle.setLibro(libro);
    }

    @Test
    public void findAll() {
        when(facturaDetalleDAO.findAll()).thenReturn(List.of(facturaDetalle));

        List<FacturaDetalle> detalles = facturaDetalleService.findAll();

        assertNotNull(detalles);
        assertEquals(1, detalles.size());
        verify(facturaDetalleDAO, Mockito.times(1)).findAll();
    }

    @Test
    public void testFindOneExistente() {
        when(facturaDetalleDAO.findById(1)).thenReturn(Optional.of(facturaDetalle));

        Optional<FacturaDetalle> resultado = facturaDetalleService.findOne(1);

        assertNotNull(resultado);
        assertEquals(2, resultado.orElse(null).getCantidad());
    }

    @Test
    public void testFindOneNoExistente() {
        when(facturaDetalleDAO.findById(2)).thenReturn(null);

        Optional<FacturaDetalle> resultado = facturaDetalleService.findOne(2);

        assertNull(resultado);
    }

    @Test
    public void testSave() {
        when(facturaDetalleDAO.save(facturaDetalle)).thenReturn(facturaDetalle);

        FacturaDetalle guardado = facturaDetalleService.save(facturaDetalle);

        assertNotNull(guardado);
        assertEquals(new BigDecimal("59.98"), guardado.getSubtotal());
    }

    @Test
    public void testUpdateExistente() {
        FacturaDetalle actualizado = new FacturaDetalle();
        actualizado.setCantidad(3);
        actualizado.setSubtotal(new BigDecimal("89.97"));

        when(facturaDetalleDAO.findById(1)).thenReturn(Optional.of(facturaDetalle));
        when(facturaDetalleDAO.save(any())).thenReturn(actualizado);

        FacturaDetalle resultado = facturaDetalleService.update(1, actualizado);

        assertNotNull(resultado);
        assertEquals(3, resultado.getCantidad());
        verify(facturaDetalleDAO, Mockito.times(1)).save(facturaDetalle);
    }

    @Test
    public void testUpdateNoExistente() {
        FacturaDetalle nuevo = new FacturaDetalle();
        when(facturaDetalleDAO.findById(999)).thenReturn(null);

        FacturaDetalle resultado = facturaDetalleService.update(999, nuevo);

        assertNull(resultado);
        verify(facturaDetalleDAO, never()).save(any());
    }

    @Test
    public void testDeleteExistente() {
        when(facturaDetalleDAO.existsById(1)).thenReturn(true);

        facturaDetalleService.delete(1);

        verify(facturaDetalleDAO).deleteById(1);
    }

    @Test
    public void testDeleteNoExistente() {
        when(facturaDetalleDAO.existsById(999)).thenReturn(false);

        facturaDetalleService.delete(999);

        verify(facturaDetalleDAO, never()).deleteById(anyInt());
    }

}
