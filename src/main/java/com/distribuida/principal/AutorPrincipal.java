package com.distribuida.principal;

import com.distribuida.model.Autor;

public class AutorPrincipal {

    public static void main(String[] args){

        Autor autor = new Autor(1, "Susana", "Horia", "Perú", "Av. Tierra a la vista", "0998645012", "susana@cienaños.com");

        System.out.println(autor.toString());
    }

}
