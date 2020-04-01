package com.pav.v1.cliente.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pav.v1.cliente.R;
import com.pav.v1.cliente.api.ClienteAdapter;
import com.pav.v1.cliente.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ConsultarClientesActivity extends AppCompatActivity {

    List<Cliente> clienteList;

    ClienteAdapter adapter;

    Cliente obj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_clientes);

        clienteList = new ArrayList<>();

        for (int i = 0; i < 50; i++){
            obj = new Cliente();

            obj.setPrimeiroNome("Cliente" +i);
            obj.setPessoaFisica(i % 2 ==0);

            clienteList.add(obj);
        }

        adapter = new ClienteAdapter(clienteList, getApplicationContext());

    }
}
