package com.example.optarea.appoptarea;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Jose on 18/01/2016.
 */
public class SubirImagenBackground extends AsyncTask<String, Void, String> {

    Context ctx;
    Button botonRojo;

    SharedPreferences preferenciasBotones;


    public SubirImagenBackground(Context ctx, Button botonRojo, SharedPreferences preferenciasBotones){
        this.ctx = ctx;
        this.botonRojo = botonRojo;
        this.preferenciasBotones = preferenciasBotones;

    }

    @Override
    protected String doInBackground(String... params) {


        String method = params[0];
        String direccion = "";

        if (method=="subirImagen"){
            String encodedImg = params[1];
            direccion = "http://comparathor.es/sendmail.php";
            return ConectarYEnviar(encodedImg, direccion, method);
        }
        else if(method=="registrar"){

        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {


        //Toast.makeText(ctx.getApplicationContext(), result, Toast.LENGTH_LONG);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        SharedPreferences.Editor editor = preferenciasBotones.edit();



        if(botonRojo.getId()==R.id.energia){
            editor.putString("energia", "usado");
            editor.commit();
        }
        else if(botonRojo.getId()==R.id.telecom) {
            editor.putString("telecom", "usado");
            editor.commit();
        }
        else if(botonRojo.getId()==R.id.seguros) {
            editor.putString("seguros", "usado");
            editor.commit();
        }
        else if(botonRojo.getId()==R.id.asesoria) {
            editor.putString("asesoria", "usado");
            editor.commit();
        }

        botonRojo.setBackgroundResource(R.drawable.button_boarder);
        botonRojo.clearAnimation();
    }

    protected String ConectarYEnviar(String encoded, String direccion, String funcion){

        HttpURLConnection con = null;
        URL url = null;
        try {
            url = new URL(direccion);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try{
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            // Activar método POST
            con.setDoOutput(true);

            OutputStream out = con.getOutputStream();
            Log.i("ComprobarBackground", "SubirImagenBackground.getView() — Funciona ");
            String data = URLEncoder.encode("cadena", "UTF-8")+" = "+ URLEncoder.encode("tuvieja", "UTF-8");
            String baseCodificada= URLEncoder.encode(encoded, "UTF-8");

            PrintStream ps = new PrintStream(con.getOutputStream());
           

            if(funcion == "subirImagen"){
                ps.print("cadena=" + baseCodificada);
            }
            else if (funcion == "registrar"){

            }


            /*
             * No envia correctamente con Buffered, (Investigar)
            //BufferedWriter bs = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            //bs.write("cadena");
            //bs.flush();
            //bs.close();*/

            Log.i("ComprobarEncoded", encoded);
            //Log.i("ComprobarbaseCod", baseCodificada);


            InputStream is = con.getInputStream();
            is.close();

            ps.flush();
            ps.close();
            out.close();

            return "Envio confirmado";

        } catch (IOException e) {
            e.printStackTrace();
            return "Envio fallido";
        } finally{
            if(con!=null)
                con.disconnect();
        }

    }
}