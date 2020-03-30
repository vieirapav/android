package com.pav.v1.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
   private SharedPreferences preferences;

    boolean isLembrarSenha = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 0° CRIE NOME DA PASTA NO APPUTIL
        // 1° OPIÇAO PARA CRIAR A PASTA
        salvarSharedPreferences();

        // 2° OPÇAO PARA PULAR O LOGIN, RETIRE AS '//'

        restaurarSharedPreferencias();

        iniciarAplicatico();

    }

    private void iniciarAplicatico() {

        // programa que faz a tela splash
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;

                if (isLembrarSenha){
                    intent = new Intent(SplashActivity.this,MainActivity.class);
                }
                else{
                    intent = new Intent(SplashActivity.this,LoginActivity.class);
                }
                startActivity(intent);
                finish();
                return;

            }
        },AppUtil.TIME_SPLASH);

    }

    private void salvarSharedPreferences(){

        // 1°CRIA a pasta de preferencias dentro do data

        preferences = getSharedPreferences(AppUtil.PREF_APP, MODE_PRIVATE);
        SharedPreferences.Editor dados = preferences.edit();

        dados.putBoolean("loginAutomatico",true);
        dados.apply();

    }

    private void restaurarSharedPreferencias(){

        // 2°AO ser ativado faz com que o app pule  o login/ ou recupera informações

        preferences = getSharedPreferences(AppUtil.PREF_APP,MODE_PRIVATE);
        isLembrarSenha = preferences.getBoolean("loaginAutomatico",false);


    }

}
