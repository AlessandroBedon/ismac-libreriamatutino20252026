package com.distribuida.model;

import com.distribuida.dao.ClienteDAO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;


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
        for(Cliente item: clientes){
            System.out.println(item.toString());
        }
    }

    @Test
    public void findOne(){
        Optional<Cliente> cliente = clienteDAO.findById(1);
        System.out.println(cliente.toString());


    }

    @Test
    public void save(){
        Cliente cliente = new Cliente(0,"1750904178","Mari1","Con1","Av. mariposa","099863578","mari@correo.com");
        clienteDAO.save(cliente);
    }

    @Test
    public void update(){
        Optional<Cliente> cliente = clienteDAO.findById(39);

        cliente.orElse(null).setCedula("1750904177");
        cliente.orElse(null).setNombre("Mari2");
        cliente.orElse(null).setApellido("Con2");
        cliente.orElse(null).setDireccion("Av. mariposa 69");
        cliente.orElse(null).setTelefono("099863166");
        cliente.orElse(null).setCorreo("mari_con@correo.com");

        clienteDAO.save(cliente.orElse(null));
    }

    @Test
    public void delete(){
        clienteDAO.deleteById(39);
    }



}




