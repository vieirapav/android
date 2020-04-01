package com.pav.v1.cliente;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pav.v1.cliente.api.AppUtil;

public class RecuperaSenhaActivity extends AppCompatActivity {

    TextView txtCadastroClienteRS, txtEMAILRS;
    EditText edtEMAILRS;
    Button btnRecuperar, btnVoltarRS;

    boolean isFormularioOK;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recupera_senha);

        initFormulario();



        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormularioOK = validarFormulario()){
                    salvarSharedPreferences();

                    Toast.makeText(getApplicationContext(), "Sua senha foi enviada para o e-mail informado", Toast.LENGTH_LONG).show();

                    Intent iVoltaTelaLogin = new Intent(RecuperaSenhaActivity.this, LoginActivity.class);
                    startActivity(iVoltaTelaLogin);
                }
            }
        });

        btnVoltarRS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iVoltarRS = new Intent(RecuperaSenhaActivity.this,LoginActivity.class);
                startActivity(iVoltarRS);
                finish();
                return;

            }
        });
    }

    private void initFormulario() {
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnVoltarRS = findViewById(R.id.btnVoltarRS);
        edtEMAILRS = findViewById(R.id.edtEMAILRS);
        txtCadastroClienteRS = findViewById(R.id.txtCadastroClienteRS);
        txtEMAILRS = findViewById(R.id.txtEMAILRS);

        isFormularioOK = false;
    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if(TextUtils.isEmpty(edtEMAILRS.getText().toString())){
            edtEMAILRS.setError("*");
            edtEMAILRS.requestFocus();
            retorno = false;
        }
        return retorno;
    }
    private void salvarSharedPreferences(){

        // 1°CRIA a pasta de preferencias dentro do data

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();


        //dados.apply salva as informaçoes como xml


    }
    private void restaurarSharedPreferencias(){

        // 2°AO ser ativado faz com que recupera informações

        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);


    }
}
