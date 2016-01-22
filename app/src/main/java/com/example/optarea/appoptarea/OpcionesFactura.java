package com.example.optarea.appoptarea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Jose on 13/01/2016.
 */

public class OpcionesFactura extends AppCompatActivity{

    static final int SELECT_FILE = 0;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    SharedPreferences preferenciasBotones;

    Button energia;
    Button telecom;
    Button seguros;
    Button asesoria;

    Button [] botones = new Button[4];
    String[] botonesS = new String[4];

    Button botonRojo;


    Animation zoom;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_factura);

        preferenciasBotones = getSharedPreferences("PreferenciasBotones", Context.MODE_PRIVATE);

        telecom = (Button)findViewById(R.id.telecom);
        energia = (Button)findViewById(R.id.energia);
        seguros = (Button)findViewById(R.id.seguros);
        asesoria = (Button)findViewById(R.id.asesoria);

        botones[0] = energia;
        botones[1] = telecom;
        botones[2] = seguros;
        botones[3] = asesoria;

        botonesS[0] = "energia";
        botonesS[1] = "telecom";
        botonesS[2] = "seguros";
        botonesS[3] = "asesoria";

        energia.setOnClickListener(RealizarCaptura);
        telecom.setOnClickListener(RealizarCaptura);

        zoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoomgenerico);

        energia.startAnimation(zoom);
        telecom.startAnimation(zoom);
        seguros.startAnimation(zoom);
        asesoria.startAnimation(zoom);
        //Typeface AgendaLight = Typeface.createFromAsset(getApplicationContext().getAssets(), "Agenda-Light.ttf");
        //energia.setTypeface(AgendaLight);

    comprobarBotones();


    }

    private OnClickListener MailLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(OpcionesFactura.this, EmailDeUsuario.class);
            startActivity(intent);
        }
    };

    private OnClickListener SubirImagenLayout = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(OpcionesFactura.this, SubirImagenPrueba.class);
            startActivity(intent);
        }
    };

    private OnClickListener EnviarImagen = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(OpcionesFactura.this, SubirImagenPrueba.class);
            startActivity(intent);
        }
    };

    private OnClickListener RealizarCaptura = new OnClickListener() {
        @Override
        public void onClick(View v) {

            botonRojo = (Button)findViewById(v.getId());
            dispatchTakePictureIntent();
        }
    };

    private void dispatchTakePictureIntent() {

        File tempFile = null;

        try {
            tempFile = File.createTempFile("img", ".jpg", getExternalCacheDir());
        } catch (IOException e) {
            e.printStackTrace();
           //AppUtil.showErrorToUser(getApplicationContext(), getResources().getString(R.string.ERROR_upload));
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempFile);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
           try{
               startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
           }catch (ClassCastException cce){
               cce.printStackTrace();
               dispatchTakePictureIntent();
           }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//
//        Intent intent = new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("temp/*");
//        startActivityForResult(Intent.createChooser(intent, "Select File"), 1);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            //String img = fileUri.getPath();

//            if(null!=data.getData()){
//
//                Uri selectedImage = data.getData();
//                String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//
//                Cursor cursor = getContentResolver().query(selectedImage,
//                        filePathColumn, null, null, null);
//
//                cursor.moveToFirst();
//
//                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                String imgPath = cursor.getString(columnIndex);
//                cursor.close();
//
//                BitmapFactory.Options options = null;
//                options = new BitmapFactory.Options();
//                options.inSampleSize = 3;
//                Bitmap bitmap = BitmapFactory.decodeFile(imgPath, options);
//
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//
//                bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
//                byte[] byte_arr = stream.toByteArray();
//
//                String encodedString = Base64.encodeToString(byte_arr, 0);
//                SubirImagenBackground subirImagen = new SubirImagenBackground(this, botonRojo, preferenciasBotones);
//                subirImagen.execute("subirImagen", encodedString);
//
//            }

//            else {
//
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                //Codificar imagen en base64
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream);
                byte[] b = byteArrayOutputStream.toByteArray();
                String encodedImage = Base64.encodeToString(b, 0);


                //Tarea en segundo plano para entrar imagen
                SubirImagenBackground subirImagen = new SubirImagenBackground(this, botonRojo, preferenciasBotones);
                subirImagen.execute("subirImagen", encodedImage);
                dispatchTakePictureIntent();
//            }
        }
    }

    //metodo para comprobar que funciones ya se han usado y bloquear botones
    private void comprobarBotones(){
        for (int i = 0; i < botonesS.length; i++) {
            String confirmacionUso = preferenciasBotones.getString(botonesS[i], "no usado");
            if (confirmacionUso.equals("usado")){
                if(i==0){
                    energia.setBackgroundResource(R.drawable.button_boarder);
                    energia.clearAnimation();
                    //energia.setClickable(false);
                }
                else if(i==1){
                    telecom.setBackgroundResource(R.drawable.button_boarder);
                    telecom.clearAnimation();
                   // telecom.setClickable(false);

                }
                else if(i==2){
                    seguros.setBackgroundResource(R.drawable.button_boarder);
                    seguros.clearAnimation();
                   // seguros.setClickable(false);

                }
                else {
                    asesoria.setBackgroundResource(R.drawable.button_boarder);
                    asesoria.clearAnimation();
                    //asesoria.setClickable(false);
                }
            }
        }
    }
}
