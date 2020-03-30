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

import com.pav.v1.cliente.controller.ClienteController;
import com.pav.v1.cliente.model.Cliente;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

public class LoginActivity extends AppCompatActivity {

    // 1°Declarar Objeto

    Cliente cliente;

    private SharedPreferences preferences;

    TextView txtRecuperarSenhaLogin, txtLerPolitica, txtVersaoApp;
    EditText edtEmailLogin, edtSenhaLogin;
    CheckBox ckboxLembrarLogin;
    Button btnCadastrarLogin, btnAcessar;

    boolean isFormularioOK, isLembrarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //2°Criar um novo metodo, initFormulario

        initFormulario();
        btnAcessar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (isFormularioOK = validarFormulario()) {

                    if (validarDadosUsuario()) {

                        salvarSharedPreferences();

                        Intent iAcessar = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(iAcessar);
                        finish();
                        return;

                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique os dados", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //O setOnClickListener adiciona a funação de clicar no botão

        txtLerPolitica.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new FancyAlertDialog.Builder(LoginActivity.this)
                        .setTitle("Política de Privacidade & Termos de Uso")
                        .setBackgroundColor(Color.parseColor("#00574B"))  //Don't pass R.color.colorvalue
                        .setMessage("TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO TEXTO")
                        .setNegativeBtnText("Discordo")
                        .setPositiveBtnBackground(Color.parseColor("#000000"))  //Don't pass R.color.colorvalue
                        .setPositiveBtnText("Concordo")
                        .setNegativeBtnBackground(Color.parseColor("#008577"))  //Don't pass R.color.colorvalue
                        .setAnimation(Animation.POP)
                        .isCancellable(true)
                        .setIcon(R.drawable.ic_launcher_foreground, Icon.Visible)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(),"Obrigado, seja bem vindo e conclua seu cadastro",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                Toast.makeText(getApplicationContext(),"Lamentamos, mas é necessário concordar com os termos",Toast.LENGTH_SHORT).show();
                                finish();
                                return;
                            }

                        })
                        .build();

            }
        });

        btnCadastrarLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent iCadastrar = new Intent(LoginActivity.this, CadastroClienteActivity.class);
                startActivity(iCadastrar);
            }
        });

        txtRecuperarSenhaLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iRecuperarSenha = new Intent(LoginActivity.this,RecuperaSenhaActivity.class);
                startActivity(iRecuperarSenha);
            }
        });

}

    private boolean validarFormulario() {

            boolean retorno = true;

            if(TextUtils.isEmpty(edtEmailLogin.getText().toString())){
                edtEmailLogin.setError("*");
                edtEmailLogin.requestFocus();
                retorno = false;
            }


            if(TextUtils.isEmpty(edtSenhaLogin.getText().toString())){
                edtSenhaLogin.setError("*");
                edtSenhaLogin.requestFocus();
                retorno = false;
        }

            return retorno;
    }


    //3°As informações do initFormulario são necessarias para colocar as variaveis na pagina

    private void initFormulario() {

        btnCadastrarLogin = findViewById(R.id.btnCadastrarLogin);
        btnAcessar = findViewById(R.id.btnAcessar);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtSenhaLogin = findViewById(R.id.edtSenhaLogin);
        ckboxLembrarLogin = findViewById(R.id.ckboxLembrarLogin);
        txtRecuperarSenhaLogin = findViewById(R.id.txtRecuperarSenhaLogin);
        txtLerPolitica = findViewById(R.id.txtLerPolitica);
        txtVersaoApp = findViewById(R.id.txtVersaoApp);

        isFormularioOK = false;
        cliente = new Cliente();

        restaurarSharedPreferencias();
    }

    public void lembrarSenha(View view) {

            isLembrarSenha = ckboxLembrarLogin.isChecked();

    }

    public boolean validarDadosUsuario (){

            return ClienteController.validarDadosCliente(cliente, edtEmailLogin.getText().toString(), edtSenhaLogin.getText().toString());
    }

    private void salvarSharedPreferences(){

        // 1°CRIA a pasta de preferencias dentro do data

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putBoolean("loginAutomatico",isLembrarSenha);
        dados.putString("emailCliente",edtEmailLogin.getText().toString());
        dados.apply();

    }

    private void restaurarSharedPreferencias(){

        // 2°AO ser ativado faz com que recupera informações

        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("loaginAutomatico",false);
        cliente.setEmail(preferences.getString("email","teste@teste.com"));
        cliente.setSenha(preferences.getString("senha","12345"));
        cliente.setPrimeiroNome(preferences.getString("primeiroNome", "Cliente"));
        cliente.setSobreNome(preferences.getString("sobreNome", "Fake"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica",true));



    }
}
