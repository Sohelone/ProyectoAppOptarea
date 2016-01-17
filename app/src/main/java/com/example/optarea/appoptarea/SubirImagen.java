package com.example.optarea.appoptarea;

import android.content.ContentValues;
import android.content.Context;
import android.content.Entity;
import android.content.Entity.NamedContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.apache.http.params.HttpParams;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Jose on 15/01/2016.
 */



public class SubirImagen extends AppCompatActivity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imagen;
    Button botonSubir;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_imagen);

        imagen = (ImageView)findViewById(R.id.imagenSubida);
        name = (EditText)findViewById(R.id.nombreImagen);
        botonSubir = (Button)findViewById(R.id.botonSubir);
        botonSubir.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imagenSubida:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;
            case R.id.botonSubir:
                Bitmap image = ((BitmapDrawable) imagen.getDrawable()).getBitmap();

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            imagen.setImageURI(selectedImage);
        }
    }

    private class UploadImage extends AsyncTask<Void, Void, Void>{

        Bitmap image;
        String name;

        public UploadImage(Bitmap image, String name){
            this.image = image;
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... params) {


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodedImage = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

            ArrayList<ContentValues> dataToSend = new ArrayList<>();
            ContentValues values = new ContentValues();
            values.put("image", encodedImage);
            values.put("name", name);



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        protected void Connectar(String encoded) throws MalformedURLException {

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                // Operaciones http
            } else {
                // Mostrar errores
            }

            HttpURLConnection con = null;
            URL url = new URL("http://optarea.comlu.com/");


            try{
                con = (HttpURLConnection)url.openConnection();

                // Activar método POST
                con.setDoOutput(true);

                // Tamaño desconocido
                con.setFixedLengthStreamingMode(0);

                OutputStream out = con.getOutputStream();
                // Usas tu método ingeniado para convertir el archivo a bytes
                BufferedOutputStream bs = new BufferedOutputStream(out);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                if(con!=null)
                    con.disconnect();
            }

        }
    }


}
