package com.distribuida.service;

import com.distribuida.dao.FacturaDetalleDAO;
import com.distribuida.model.Cliente;
import com.distribuida.model.FacturaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaDetalleServiceImpl implements FacturaDetalleService {

    @Autowired
    private FacturaDetalleDAO facturaDetalleDAO;

    @Override
    public List<FacturaDetalle> findAll() {
        return facturaDetalleDAO.findAll();
    }

    @Override
    public Optional<FacturaDetalle> findOne(int id) {
        return facturaDetalleDAO.findById(id);
    }

    @Override
    public FacturaDetalle save(FacturaDetalle facturaDetalle) {
        return facturaDetalleDAO.save(facturaDetalle);
    }

    @Override
    public FacturaDetalle update(int id, FacturaDetalle facturaDetalle) {
        Optional<FacturaDetalle> facturaDetalleExistente = facturaDetalleDAO.findById(id);

        if (facturaDetalleExistente == null) {
            return null;
        }
        facturaDetalleExistente.orElse(null).setSubtotal(facturaDetalle.getSubtotal());
        facturaDetalleExistente.orElse(null).setCantidad(facturaDetalle.getCantidad());

        return facturaDetalleDAO.save(facturaDetalleExistente.orElse(null));
    }

        @Override
        public void delete ( int id){
            if (facturaDetalleDAO.existsById(id)) {
                facturaDetalleDAO.deleteById(id);
            }
        }
    }
