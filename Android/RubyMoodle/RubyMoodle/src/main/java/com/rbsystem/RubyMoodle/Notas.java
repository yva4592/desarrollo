package com.rbsystem.RubyMoodle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import AccesoDatos.Conectar;
import entidad.Entidad_notas;
import library.Adapter_notas;
import library.Httppostaux;

import java.util.ArrayList;

/**
 * Created by josépablo on 19-12-13.
 */
public class Notas extends ActionBarActivity {

    String user;
    String user_id;
    String token;
    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;

    //este objeto se ocupa para las variables porcentaje_presente, dias presente, dias ausente, dias ausente justificado
    Entidad_notas eno = new Entidad_notas();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user  = extras.getString("user");//usuario
            user_id = extras.getString("user_id");
            token = Integer.toString(extras.getInt("token"));
            new asincrona().execute(user);
        }
        else
        {
            user="error";
            user_id="error";
            token ="error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
        // porcentajepresente.setText("bien");
    }
    public ArrayList<Entidad_notas> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_notas> array_notas = new ArrayList<Entidad_notas>();
        Entidad_notas notas;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "notas"));
        postparameterssend.add(new BasicNameValuePair("username",user));
        postparameterssend.add(new BasicNameValuePair("token",token));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase notas", "cadena de envio notas:" + URL_connect + postparameterssend);
        Log.e("Clase notas", "cadena de regreso notas:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("id").equals("error_sesion"))
                {
                    Intent i=new Intent(Notas.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                //else
                //{
                    for (int i=0; i<jdata.length();i++)
                    {
                        if(jdata.getJSONObject(i).getInt("nota") >= 50){
                            notas = new Entidad_notas(getResources().getDrawable(R.drawable.icono_aceptar),jdata.getJSONObject(i).getString("curso"),jdata.getJSONObject(i).getString("item"),jdata.getJSONObject(i).getString("nota"));
                        }
                        else{
                            notas = new Entidad_notas(getResources().getDrawable(R.drawable.icono_rechazar),jdata.getJSONObject(i).getString("curso"),jdata.getJSONObject(i).getString("item"),jdata.getJSONObject(i).getString("nota"));
                        }
                        array_notas.add(notas);
                    }
                //}
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                notas = new Entidad_notas(getResources().getDrawable(R.drawable.icono_interrogracion)," ?   ","   ?    "," ?   ");
                array_notas.add(notas);
            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
           // Toast toast1 = Toast.makeText(getApplicationContext(),"No se ha encontrado notas", Toast.LENGTH_SHORT);
            //toast1.show();
        }
        return array_notas;
    }

    public void cargaListado(ArrayList<Entidad_notas> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listanotas);
        Adapter_notas adapter = new Adapter_notas(this, datos);
        lista.setAdapter(adapter);
    }


    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_notas>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(Notas.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_notas> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
            return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_notas> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_notas> cadena = result;
                cargaListado(cadena);
            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener las notas", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(Notas.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
