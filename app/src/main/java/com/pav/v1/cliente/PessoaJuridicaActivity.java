package com.pav.v1.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import com.pav.v1.cliente.model.Cliente;
import com.pav.v1.cliente.model.ClientePJ;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;


public class PessoaJuridicaActivity extends AppCompatActivity {

    ClientePJ novoClientePJ;
    Cliente novoCliente;

    private SharedPreferences preferences;

    TextView txtCadastroPessoaJuridica, txtCNPJ, txtRazaoSocial, txtDataAbertura;
    EditText edtCNPJ, edtRazaoSocial, edtDataAbertura;
    Button btnSalvarContinuarCNPJ, btnVoltarCNPJ, btnCancelarCNPJ;
    CheckBox ckboxSimplesNacional, ckboxMEI;

    boolean isFormularioOK, isSimpleNacional, isMEI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_juridica);

        initFormulario();
        isFormularioOK = validarFormulario();
        btnSalvarContinuarCNPJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFormularioOK = validarFormulario()) {
                    novoClientePJ.setCnpj(edtCNPJ.getText().toString());
                    novoClientePJ.setRazaoSocial(edtRazaoSocial.getText().toString());
                    novoClientePJ.setDataAbertura(edtDataAbertura.getText().toString());
                    novoClientePJ.setSimplesNacional(isSimpleNacional);
                    novoClientePJ.setMei(isMEI);

                    salvarSharedPreferences();

                    // ------- PROBLEMA FUTURO ------
                    Intent iPessoaFisica = new Intent(PessoaJuridicaActivity.this, CredencialAcessoActivity.class);
                    startActivity(iPessoaFisica);
                }
            }
        });
        btnCancelarCNPJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new FancyAlertDialog.Builder(PessoaJuridicaActivity.this)
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
        // -----------------------------


        btnVoltarCNPJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVoltarCNPJ = new Intent(PessoaJuridicaActivity.this, CadastroClienteActivity.class);
                startActivity(iVoltarCNPJ);
            }
        });
    }


    public void simplesNacional(View view) {

        isSimpleNacional = ckboxSimplesNacional.isChecked();
    }

    public void MEI(View view) {

        isMEI = ckboxMEI.isChecked();
    }

    private void initFormulario() {
        btnSalvarContinuarCNPJ = findViewById(R.id.btnSalvarContinuarCNPJ);
        btnVoltarCNPJ = findViewById(R.id.btnVoltarCNPJ);
        btnCancelarCNPJ = findViewById(R.id.btnCancelarCNPJ);
        edtCNPJ = findViewById(R.id.edtCNPJ);
        edtRazaoSocial = findViewById(R.id.edtRazaoSocial);
        edtDataAbertura = findViewById(R.id.edtDataAbertura);
        txtCadastroPessoaJuridica = findViewById(R.id.txtCadastroPessoaJuridica);
        txtCNPJ = findViewById(R.id.txtCNPJ);
        txtDataAbertura = findViewById(R.id.txtDataAbertura);
        txtRazaoSocial = findViewById(R.id.txtRazaoSocial);
        ckboxSimplesNacional = findViewById(R.id.ckboxSimplesNacional);
        ckboxMEI = findViewById(R.id.ckboxMEI);

        isFormularioOK = false;

        novoCliente = new Cliente();
        novoClientePJ = new ClientePJ();

        restaurarSharedPreferencias();

    }

    private boolean validarFormulario() {

        boolean retorno = true;

        if(TextUtils.isEmpty(edtCNPJ.getText().toString())){
            edtCNPJ.setError("*");
            edtCNPJ.requestFocus();
            retorno = false;
        }

        if(TextUtils.isEmpty(edtRazaoSocial.getText().toString())){
            edtRazaoSocial.setError("*");
            edtRazaoSocial.requestFocus();
            retorno = false;
        }

        if(TextUtils.isEmpty(edtDataAbertura.getText().toString())){
            edtDataAbertura.setError("*");
            edtDataAbertura.requestFocus();
            retorno = false;
        }

        return retorno;

    }
    private void salvarSharedPreferences(){

        // 1°CRIA a pasta de preferencias dentro do data

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putString("Cnpj",edtCNPJ.getText().toString());
        dados.putString("razaoSocial",edtRazaoSocial.getText().toString());
        dados.putString ("dataAbertura", edtDataAbertura.getText().toString());
        dados.putBoolean("simplesNacional",isSimpleNacional);
        dados.putBoolean("Mei",isMEI);
        //dados.apply salva as informaçoes como xml
        dados.apply();

    }
    private void restaurarSharedPreferencias(){

        // 2°AO ser ativado faz com que recupera informações

        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);


    }

}
