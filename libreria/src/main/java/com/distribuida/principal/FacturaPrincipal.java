package com.distribuida.principal;

import com.distribuida.model.Cliente;
import com.distribuida.model.Factura;

import java.util.Date;

public class FacturaPrincipal {
    public static void  main(String[] args){

        Cliente cliente = new Cliente(1, "1750904121", "Mari", "Con", "Av. 69", "0998631058", "mari@con.com");

        Factura factura = new Factura();

        factura.setIdFactura(1);
        factura.setNumFactura("FAC-0001");
        factura.setFecha(new Date());
        factura.setTotalNeto(50.00);
        factura.setIva(15.00);
        factura.setTotal(65.00);
        //INYECCION
        factura.setCliente(cliente);

        System.out.println(factura.toString());
    }
}
