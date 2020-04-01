package com.pav.v1.cliente.api;

import java.util.Calendar;

/**
 * Classes de apoio que podem ser reutilizados em todo o projeto
 *
 * Versão v2
 */

public class AppUtil {

    public static final int TIME_SPLASH = 5*1000;
    public static final String PREF_APP = "app_cliente_pref";
    public static final String LOG_APP = "CLIENTE_LOG";


    public static String getDataAtual(){
        String dia,mes,ano;
        String dataAtual = "00/00/0000";

        try  {

            Calendar calendar = Calendar.getInstance();

            int iDia = calendar.get(calendar.DAY_OF_MONTH);
            int iMes = calendar.get(calendar.MONTH)+1;

            dia = (iDia <10) ? "0"+iDia : Integer.toString(iDia);
            mes = (iMes <10) ? "0"+iMes : Integer.toString(iMes);
            ano = String.valueOf(calendar.get(Calendar.YEAR));


            dataAtual = dia+"/"+mes+"/"+ano;

        }catch (Exception e){

        }

        return dataAtual;

    }
    public static String getHoraAtual(){

        String horaAtual = "00:00:00";
        String hora, minuto, segundo;

        try  {

            Calendar calendar = Calendar.getInstance();
            int iHora = calendar.get(Calendar.HOUR_OF_DAY);
            int iMinuto = calendar.get(Calendar.MINUTE);
            int iSegundo = calendar.get(Calendar.SECOND);

            hora = Integer.toString(iHora);
            minuto = (iMinuto <= 9) ? "0"+iMinuto : Integer.toString(iMinuto);
            segundo = (iSegundo<=9) ? "0"+iSegundo: Integer.toString(iSegundo);

            horaAtual = hora+":"+minuto+":"+segundo;

        }
        catch (Exception e){

        }

        return horaAtual;

    }

}
