package com.pav.v1.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pav.v1.cliente.model.Cliente;
import com.pav.v1.cliente.model.ClientePF;
import com.pav.v1.cliente.model.ClientePJ;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //Nome da classe - declarar objeto

    Cliente cliente;
    ClientePF clientePF;
    ClientePJ clientePJ;

    TextView txtNomeCliente,app_nome;
    Button btnMeusDados, btnAtualizarDados, btnExcluirConta, btnConsultaCliente, btnSairAplicativo,btnVoltarPrincipal;

    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFormulario();

        //btnSairAplicativo.setOnClickListener(new View.OnClickListener() {
            //@Override
           // public void onClick(View v) {
               // Intent iSair = new Intent(Intent.ACTION_MAIN);
               // iSair.addCategory(Intent.CATEGORY_HOME);
               // iSair.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               // startActivity(iSair);
            //}
        //});

        btnVoltarPrincipal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVoltarPrincipal = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(iVoltarPrincipal);
                finish();
                return;
            }
        });

    }

    private void initFormulario() {

        txtNomeCliente = findViewById(R.id.txtNomeCliente);
        btnMeusDados = findViewById(R.id.btnMeusDados);
        btnAtualizarDados = findViewById(R.id.btnAtualizarDados);
        btnExcluirConta = findViewById(R.id.btnExcluirConta);
        btnConsultaCliente = findViewById(R.id.btnConsultaCliente);
        btnSairAplicativo = findViewById(R.id.btnSairAplicativo);
        btnVoltarPrincipal = findViewById(R.id.btnVoltarPrincipal);

        cliente = new Cliente();
        clientePF = new ClientePF();
        clientePJ = new ClientePJ();

    // DEVE SER COLOCADO DEPOIS, CASO CONTRARIO DA ERRO//
        restaurarSharedPreferencias();

        //PLOTANDO DADO SALVO NO LUGAR DO txtNomeCliente
        //2° aparece a mensagem 'Bem vindo' + o nome
        txtNomeCliente.setText("Bem vindo(a), " + cliente.getPrimeiroNome());

    }

    private void salvarSharedPreferences(){

        // 1°CRIA a pasta de preferencias dentro do data

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);



    }
    private void restaurarSharedPreferencias(){

        // 2°AO ser ativado faz com que recupera informações

        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);

        // RECUPERANDO DADO
        cliente.setPrimeiroNome(preferences.getString("primeiroNome","NULO"));
        cliente.setSobreNome(preferences.getString("sobreNome","NULO"));
        cliente.setEmail(preferences.getString("email","NULO"));
        cliente.setSenha(preferences.getString("senha","NULO"));
        cliente.setPessoaFisica(preferences.getBoolean("pessoaFisica",true));

        clientePF.setCpf(preferences.getString("Cpf","NULO"));
        clientePF.setNomeCompleto(preferences.getString("nomeCompleto","NULO"));

        clientePJ.setCnpj(preferences.getString("Cnpj","NULO"));
        clientePJ.setRazaoSocial(preferences.getString("RazaoSocial","NULO"));
        clientePJ.setDataAbertura(preferences.getString("dataAbertura","NULO"));
        clientePJ.setMei(preferences.getBoolean("MEI",false));
        clientePJ.setSimplesNacional(preferences.getBoolean("simplesNacional",false));

        //PLOTANDO DADO SALVO NO LUGAR DO txtNomeCliente

        //1°So aparece apenas o nome na tela.
        //txtNomeCliente.setText(preferences.getString("nomeCompleto", "*"));


    }
// NÃO TEM BOTAO POIS USOU COMANDO 'onClick'
    public void meusDados(View view) {


        Log.i(AppUtil.LOG_APP,"--- DADOS CLIENTE ---");
        Log.i(AppUtil.LOG_APP,"Primeiro Nome: " +cliente.getPrimeiroNome());
        Log.i(AppUtil.LOG_APP,"Sobre Nome: " +cliente.getSobreNome());
        Log.i(AppUtil.LOG_APP,"Email: " +cliente.getEmail());
        Log.i(AppUtil.LOG_APP,"Senha Nome: " +cliente.getSenha());
        Log.i(AppUtil.LOG_APP,"ID: " +cliente.getId());

        Log.i(AppUtil.LOG_APP,"CPF: " +clientePF.getCpf());
        Log.i(AppUtil.LOG_APP,"Nome Completo: " +clientePF.getNomeCompleto());


        if(!cliente.isPessoaFisica()){
            Log.i(AppUtil.LOG_APP,"CNPJ: " +clientePJ.getCnpj());
            Log.i(AppUtil.LOG_APP,"Razao Social: " +clientePJ.getRazaoSocial());
            Log.i(AppUtil.LOG_APP,"Data de Abertura " +clientePJ.getDataAbertura());
            Log.i(AppUtil.LOG_APP,"Simples Nacional: " +clientePJ.isSimplesNacional());
            Log.i(AppUtil.LOG_APP,"MEI: " +clientePJ.isMei());
        }

    }

    public void atualizarDados (View view){

    }
    public void consultarCliente(View view) {

    }

    public void excluirConta(View view) {
        new FancyAlertDialog.Builder(MainActivity.this)
                .setTitle("EXCLUIR CONTA")
                .setBackgroundColor(Color.parseColor("#00574B"))  //Don't pass R.color.colorvalue
                .setMessage("Confirma EXCLUSÃO definitiva da sua conta?")
                .setNegativeBtnText("NÃO")
                .setPositiveBtnBackground(Color.parseColor("#000000"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#008577"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_launcher_foreground, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome()+", sua conta foi excluida, esperamos de retorne em breve!",Toast.LENGTH_SHORT).show();

                        cliente = new Cliente();
                        clientePF = new ClientePF();
                        clientePJ = new ClientePJ();

                        //Lembrar Senha automaticamente, tem que ser resetado

                        //Habilitar salvarsharedpreferences, exclui de verdade a conta
                        salvarSharedPreferences();
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome()+ "continue sua jornada",Toast.LENGTH_SHORT).show();

                    }

                })
                .build();
    }

    public void sairAplicativo(View view) {
        new FancyAlertDialog.Builder(MainActivity.this)
                .setTitle("SAIR DO APLICATIVO")
                .setBackgroundColor(Color.parseColor("#00574B"))  //Don't pass R.color.colorvalue
                .setMessage("Confirma sua saida do Aplicativo")
                .setNegativeBtnText("RETORNAR")
                .setPositiveBtnBackground(Color.parseColor("#000000"))  //Don't pass R.color.colorvalue
                .setPositiveBtnText("SIM")
                .setNegativeBtnBackground(Color.parseColor("#008577"))  //Don't pass R.color.colorvalue
                .setAnimation(Animation.POP)
                .isCancellable(true)
                .setIcon(R.drawable.ic_launcher_foreground, Icon.Visible)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome()+", volte sempre!",Toast.LENGTH_SHORT).show();
                        Intent iSair = new Intent(Intent.ACTION_MAIN);
                        iSair.addCategory(Intent.CATEGORY_HOME);
                        iSair.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(iSair);
                        finish();
                        return;
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        Toast.makeText(getApplicationContext(),cliente.getPrimeiroNome()+ "continue sua jornada",Toast.LENGTH_SHORT).show();

                    }

                })
                .build();
    }

}
