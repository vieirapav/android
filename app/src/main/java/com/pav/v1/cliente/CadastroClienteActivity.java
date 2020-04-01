package com.pav.v1.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextUtils;

import com.pav.v1.cliente.api.AppUtil;
import com.pav.v1.cliente.model.Cliente;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

public class CadastroClienteActivity extends AppCompatActivity {
    //1°Declare objetos
    // Adiconar Cliente e SharedPreferences
    // Adicone os botoes
    //2°Adicionar initFormulario()no 'onCreate'
    //3°Criar initFormulario
    //CheckBox crie public void do ckbox



    Cliente novoCliente;
    private SharedPreferences preferences;

    TextView txtPrimeiroNome, txtSobreNome;
    EditText edtPrimeiroNome, edtSobreNome;
    CheckBox ckboxPessoaFisica;
    Button btnSalvarContinuar, btnCancelar;

    boolean isFormularioOK, isPessoaFisica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        initFormulario();

        btnSalvarContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFormularioOK = validarFormulario()) {

                    //Essas informaçoes serao salvas no sharedpreferences, devem ser citados no salvar sharedPreferences
                    novoCliente.setPrimeiroNome(edtPrimeiroNome.getText().toString());
                    novoCliente.setSobreNome(edtSobreNome.getText().toString());
                    novoCliente.setPessoaFisica(isPessoaFisica);

                    salvarSharedPreferences();

                    if (isPessoaFisica){
                        //se for pessoa fisica, carrega a tela do cadastro de CPF
                        Intent iCadastroCPF = new Intent(CadastroClienteActivity.this,PessoaFisicaActivity.class);
                        startActivity(iCadastroCPF);

                    }else{
                        //se não,carrega a tela de cadastro de CNPJ
                        // ------ PROBLEMA FUTURO ----- Toda pessoa juridica é fisica
                        Intent iCadastroCNPJ = new Intent(CadastroClienteActivity.this,PessoaFisicaActivity.class);
                        startActivity(iCadastroCNPJ);

                    }
                }
        }
    });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// ----- PROGRAMAÇAO DE TERCEIRO -----

                new FancyAlertDialog.Builder(CadastroClienteActivity.this)
                        .setTitle("Confirma o Cancelamento")
                        .setBackgroundColor(Color.parseColor("#00574B"))  //Don't pass R.color.colorvalue
                        .setMessage("Deseja realmente Cancelar")
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
  //-----------------------------

            }
        });

    }

    //Necessario para checkbox
    public void pessoasFisica(View view) {

        isPessoaFisica = ckboxPessoaFisica.isChecked();
    }

    private void initFormulario () {

        edtPrimeiroNome = findViewById(R.id.edtPrimeiroNome);
        edtSobreNome = findViewById(R.id.edtSobreNome);
        ckboxPessoaFisica = findViewById(R.id.ckboxPessoaFisica);
        txtPrimeiroNome = findViewById(R.id.txtPrimeiroNome);
        txtSobreNome = findViewById(R.id.txtSobreNome);
        btnSalvarContinuar = findViewById(R.id.btnSalvarContinuar);
        btnCancelar = findViewById(R.id.btnCancelar);

        isFormularioOK = false;

        novoCliente = new Cliente();

        restaurarSharedPreferencias();

    }

    private boolean validarFormulario(){

        boolean retorno = true;

        if(TextUtils.isEmpty(edtPrimeiroNome.getText().toString())){
            edtPrimeiroNome.setError("*");
            edtPrimeiroNome.requestFocus();
            retorno = false;
        }


        if(TextUtils.isEmpty(edtSobreNome.getText().toString())){
            edtSobreNome.setError("*");
            edtSobreNome.requestFocus();
            retorno = false;
        }

        return retorno;

    }

    private void salvarSharedPreferences(){

        // 1°CRIA a pasta de preferencias dentro do data

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        //São tres dados, que estão salvos em 'novoCliente',foi o primeiro a ser referenciado
        dados.putString("primeiroNome",novoCliente.getPrimeiroNome());
        dados.putString("sobreNome",novoCliente.getSobreNome());
        dados.putBoolean("pessoaFisica",novoCliente.isPessoaFisica());
        //dados.apply salva as informaçoes como xml
        dados.apply();

    }
    private void restaurarSharedPreferencias(){

        // 2°AO ser ativado faz com que recupera informações

        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);


    }
}
