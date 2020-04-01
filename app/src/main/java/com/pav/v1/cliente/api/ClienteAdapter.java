package com.pav.v1.cliente.api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pav.v1.cliente.R;
import com.pav.v1.cliente.model.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    private List<Cliente> aClientes; //Lista de clientes do adapter - aClientes
    private Context aContext;

    public ClienteAdapter(List<Cliente> aClientes, Context aContext) {
        this.aClientes = aClientes;
        this.aContext = aContext;
    }

    @NonNull
    @Override
    public ClienteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //--View linhaView = inflater.inflate(R.layout);--//

        return null;



    }

    @Override
    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
