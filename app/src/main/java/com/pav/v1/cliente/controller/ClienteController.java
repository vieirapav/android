package com.pav.v1.cliente.controller;

import com.pav.v1.cliente.model.Cliente;

public class ClienteController {


    public static boolean validarDadosCliente(Cliente cliente, String email, String senha) {

// Email fornecido deve ser igual ao email 'fake', o mesmo vale para senha

     boolean retorno = ((cliente.getEmail().equals(email)) && (cliente.getSenha().equals(senha)));

    return retorno;
    }

    public static Cliente getClienteFake(){

        Cliente fake = new Cliente();
        fake.setPrimeiroNome("Priscila");
        fake.setSobreNome("Almeida");
        fake.setEmail("priscila@teste.com");
        fake.setSenha("12345");
        fake.setPessoaFisica(true);

        return fake;
    }

}

