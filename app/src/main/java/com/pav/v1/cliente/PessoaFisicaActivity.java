package com.pav.v1.cliente;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.pav.v1.cliente.api.AppUtil;
import com.pav.v1.cliente.model.Cliente;
import com.pav.v1.cliente.model.ClientePF;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

public class PessoaFisicaActivity extends AppCompatActivity {

    ClientePF novoClientePF;

    Cliente novoCliente;

    private SharedPreferences preferences;

    TextView txtCadastroPessoaFisica, txtCPF, txtNomeCompleto;
    EditText edtCPF, edtNomeCompleto;
    Button btnSalvarContinuarCPF, btnVoltarCPF, btnCancelarCPF;

    boolean isFormularioOK, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_fisica);

        initFormulario();
        isFormularioOK = validarFormulario();

        btnSalvarContinuarCPF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFormularioOK = validarFormulario()){
                    novoClientePF.setCpf(edtCPF.getText().toString());
                    novoClientePF.setNomeCompleto(edtNomeCompleto.getText().toString());

                    salvarSharedPreferences();
                    if (isPessoaFisica){
                        //se for pessoa fisica, carrega a tela de credencial
                        Intent iCadastroCPF = new Intent(PessoaFisicaActivity.this,CredencialAcessoActivity.class);
                        startActivity(iCadastroCPF);

                    }else{
                        //se não,carrega a tela de cadastro de CNPJ
                        Intent iCadastroCNPJ = new Intent(PessoaFisicaActivity.this,PessoaJuridicaActivity.class);
                        startActivity(iCadastroCNPJ);

                    }
                }

            }
        });

// ----- PROGRAMAÇAO DE TERCEIRO -----

        btnCancelarCPF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FancyAlertDialog.Builder(PessoaFisicaActivity.this)
                        .setTitle("Confirma o Cancelamento")
                        .setBackgroundColor(Color.parseColor("#00574B"))  //Don't pass R.color.colorvalue
                        .setMessage("Deseja realmente Cancelar?")
                        .setNegativeBtnText("Não")
                        .setPositiveBtnBackground(Color.parseColor("#000000"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("Sim")
                        .setNegativeBtnBackground(Color.parseColor("#008577"))  //Don't pass R.color.colorvalue
                        .setAnimation(Animation.POP)
                        .isCancellable(true)
                        .setIcon(R.drawable.ic_launcher_foreground, Icon.Visible)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(),"Cancelado com sucesso",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(),"Continue seu cadastro",Toast.LENGTH_SHORT).show();

                            }

                        })
                        .build();
            }
        });
//-----------------------------

        btnVoltarCPF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCancelar = new Intent(PessoaFisicaActivity.this, CadastroClienteActivity.class);
                startActivity(iCancelar);
                finish();
                return;

            }
        });

        }

    private void initFormulario() {
        btnSalvarContinuarCPF = findViewById(R.id.btnSalvarContinuarCPF);
        btnVoltarCPF = findViewById(R.id.btnVoltarCPF);
        btnCancelarCPF = findViewById(R.id.btnCancelarCPF);
        edtCPF = findViewById(R.id.edtCPF);
        edtNomeCompleto = findViewById(R.id.edtNomeCompleto);
        txtCadastroPessoaFisica = findViewById(R.id.txtCadastroPessoaFisica);
        txtCPF = findViewById(R.id.txtCPF);
        txtNomeCompleto = findViewById(R.id.txtNomeCompleto);

        isFormularioOK = false;

        novoCliente = new Cliente();
        novoClientePF = new ClientePF();

        restaurarSharedPreferencias();

    }
    private boolean validarFormulario() {

        boolean retorno = true;

        if(TextUtils.isEmpty(edtCPF.getText().toString())){
            edtCPF.setError("*");
            edtCPF.requestFocus();
            retorno = false;
        }


        if(TextUtils.isEmpty(edtNomeCompleto.getText().toString())){
            edtNomeCompleto.setError("*");
            edtNomeCompleto.requestFocus();
            retorno = false;
        }

        return retorno;
    }

    private void salvarSharedPreferences(){

        // 1°CRIA a pasta de preferencias dentro do data

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("Cpf",edtCPF.getText().toString());
        dados.putString("nomeCompleto",edtNomeCompleto.getText().toString());

        //dados.apply salva as informaçoes como xml
        dados.apply();

        //dados.apply salva as informaçoes como xml


    }
    private void restaurarSharedPreferencias(){

        // 2°AO ser ativado faz com que recupera informações

        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        isPessoaFisica = preferences.getBoolean("pessoaFisica",true);

    }

}
