package com.distribuida.dao;

import com.distribuida.model.Cliente;
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

public class ClienteTestIntegracion {

    @Autowired
    public ClienteDAO clienteDAO;

    @Test
    public void findAll(){
        List<Cliente> clientes = clienteDAO.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size() > 0);
        for(Cliente item: clientes){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Cliente> cliente = clienteDAO.findById(1);
        assertTrue(cliente.isPresent(), "El cliente con ID=1 si existe");
        System.out.println(cliente.toString());
    }

    @Test
    public void save(){
        Cliente cliente = new Cliente(0,"1750904178","Mari","Con","Av. mariposa","099863578","mari@correo.com");
        Cliente clienteGuardado = clienteDAO.save(cliente);
        assertNotNull(clienteGuardado,"El cliente nuevo se guardó correctamente");
        assertEquals("1750904178", clienteGuardado.getCedula());
        assertEquals("Mari", clienteGuardado.getNombre());
    }

    @Test
    public void update(){
        Optional<Cliente> cliente = clienteDAO.findById(42);

        cliente.orElse(null).setCedula("1750904177");
        cliente.orElse(null).setNombre("Mari2");
        cliente.orElse(null).setApellido("Con2");
        cliente.orElse(null).setDireccion("Av. mariposa 69");
        cliente.orElse(null).setTelefono("099863166");
        cliente.orElse(null).setCorreo("mari_con@correo.com");


        Cliente clienteActualizado = clienteDAO.save(cliente.orElse(null));
        assertNotNull(clienteActualizado,"El cliente fue actualizado");
        assertEquals("Mari2", clienteActualizado.getNombre());
        assertEquals("Con2", clienteActualizado.getApellido());
    }
//
    @Test
    public void delete() {
        if (clienteDAO.existsById(42)) {
            clienteDAO.deleteById(42);
        }
        assertFalse(clienteDAO.existsById(42),"El dato fue eliminado");
    }


}




