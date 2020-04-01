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
import android.widget.Toast;

import com.pav.v1.cliente.api.AppUtil;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;


public class CredencialAcessoActivity extends AppCompatActivity {

    Button btnVoltar, btnCadastro2;
    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;
    EditText edtRepitaSenha;
    CheckBox ckboxTermos;
    boolean isFormularioOK, isPessoaFisica;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        initFormulario();

        btnVoltar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent iVoltar = new Intent(CredencialAcessoActivity.this, CadastroClienteActivity.class);
                startActivity(iVoltar);
            }
        });

        btnCadastro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isFormularioOK = true;

                if (TextUtils.isEmpty(edtNome.getText().toString())) {
                    edtNome.setError("*");
                    edtNome.requestFocus();
                    isFormularioOK = false;
                }
                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    edtEmail.setError("*");
                    edtEmail.requestFocus();
                    isFormularioOK = false;
                }
                if (TextUtils.isEmpty(edtSenha.getText().toString())) {
                    edtSenha.setError("*");
                    edtSenha.requestFocus();
                    isFormularioOK = false;
                }
                if (TextUtils.isEmpty(edtRepitaSenha.getText().toString())) {
                    edtRepitaSenha.setError("*");
                    edtRepitaSenha.requestFocus();
                    isFormularioOK = false;
                }

                if (!ckboxTermos.isChecked()) {
                    isFormularioOK = false;
                }

                if (isFormularioOK) {

                    if (!validarSenha()) {
                        edtSenha.setError("*");
                        edtRepitaSenha.setError("*");
                        edtSenha.requestFocus();
                        // ----- SUBSTITUIDO -----
                       // Toast.makeText(getApplicationContext(), "As senha digitadas não conferem!", Toast.LENGTH_LONG).show();
                        // -----------------------

                        new FancyAlertDialog.Builder(CredencialAcessoActivity.this)
                                .setTitle("ATENÇÃO!")
                                .setBackgroundColor(Color.parseColor("#00574B"))  //Don't pass R.color.colorvalue
                                .setMessage("As senhas digitadas não são iguais, por favor tente novamente.")
                                .setPositiveBtnText("CONTINUAR")
                                .setNegativeBtnBackground(Color.parseColor("#008577"))  //Don't pass R.color.colorvalue
                                .setAnimation(Animation.POP)
                                .isCancellable(true)
                                .setIcon(R.drawable.ic_launcher_foreground, Icon.Visible)
                                .OnPositiveClicked(new FancyAlertDialogListener() {
                                    @Override
                                    public void OnClick() {
                                    }
                                })
                                .build();
                    } else {

                        salvarSharedPreferences();

                        Intent iCadastro = new Intent(CredencialAcessoActivity.this, MainActivity.class);
                        startActivity(iCadastro);
                    }
                }
            }
        });

    }


        private void initFormulario () {

            btnVoltar = findViewById(R.id.btnVoltar);
            btnCadastro2 = findViewById(R.id.btnCadastro2);
            edtNome = findViewById(R.id.edtNome);
            edtEmail = findViewById(R.id.edtEmail);
            edtSenha = findViewById(R.id.edtSenha);
            edtRepitaSenha = findViewById(R.id.edtRepitaSenha);
            ckboxTermos = findViewById(R.id.ckboxTermos);
            isFormularioOK = false;

            restaurarSharedPreferencias();
        }


    public void ValidarTermo(View view) {

        if (!ckboxTermos.isChecked()) {
            Toast.makeText(getApplicationContext(), "É necessário aceitar os termos de uso para continuar o cadastro", Toast.LENGTH_LONG).show();
        }
    }

    public boolean validarSenha() {
        boolean retorno = false;

        int senhaA, senhaB;

        senhaA = Integer.parseInt(edtSenha.getText().toString());
        senhaB = Integer.parseInt(edtRepitaSenha.getText().toString());

        retorno = (senhaA == senhaB);
        return retorno;
    }
    private void salvarSharedPreferences(){

        // 1°CRIA a pasta de preferencias dentro do data

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();
        dados.putString("email",edtEmail.getText().toString());
        dados.putString("senha",edtSenha.getText().toString());
        dados.apply();

    }
    private void restaurarSharedPreferencias() {

        // 2°AO ser ativado faz com que recupera informações
        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        //FAZER O COMPLETAR O NOME DA PESSOA/RAZAO SOCAIL APARECER AUTOMATICAMENTE  edtNome
        isPessoaFisica = preferences.getBoolean("pessoaFisica",true);
        if(isPessoaFisica)
            edtNome.setText( preferences.getString("nomeCompleto","Verifique os dados"));
        else
            edtNome.setText( preferences.getString("razaoSocial","Verifique os dados"));
    }
}
