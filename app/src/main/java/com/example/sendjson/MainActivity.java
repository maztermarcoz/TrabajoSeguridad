package com.example.sendjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText editTex;
    private Button enviar;

    private void iniciarControles (){
        editTex = (EditText)findViewById(R.id.editNombre);
        enviar = (Button)findViewById(R.id.btn);

        //Asignar controlador
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formJson();
                //Llamada al metodo de enviar()

            }
        });

    }
    //Metodo para formar el Json que se enviara al backend
    private void formJson() {
        //Se incluye libreria JSONObject
        JSONObject jo = new JSONObject();

        //Agregar los datos al objeto, se indica la key
        //Lado izquierda es la key, derecho es el value
        try {
            jo.put("id", 0); // 0, el campo de la tabla es autoincrement

            jo.put("nombre", editTex.getText().toString());

            //String que representa el url del backend
            String url = "http://192.168.100.117:8081/contacto/add"; // es el metodo add que esta en el restcontroller
            //la direccion ip, es la del servidor


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
private String postJsonObject(String postUrl, JSONObject jsonObject){

        //permite el uso y conexion con el backend, para envio y recepcion de datos, por el
    HttpURLConnection conn = null;

            StringBuffer response = null;

    try {
        URL url = new URL(postUrl);

        conn = (HttpURLConnection)url.openConnection();

        conn.setRequestProperty("Content-Type", "application/json");

        conn.setRequestMethod("POST");

        OutputStream out = new BufferedOutputStream(conn.getOutputStream());

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

        writer.write(jsonObject.toString());
        writer.close();
        out.close();



    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        conn.disconnect();
    }
    return null;
}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarControles();
        //formJson();




    }
}