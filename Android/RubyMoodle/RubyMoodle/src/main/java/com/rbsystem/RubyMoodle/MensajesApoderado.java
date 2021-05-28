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

import java.util.ArrayList;

import AccesoDatos.Conectar;
import entidad.Entidad_mensajes_apoderado;
import entidad.Entidad_mensajes_leidos;
import library.Adapter_mensajes_apoderado;
import library.Adapter_mensajes_leidos;
import library.Httppostaux;

/**
 * Created by josépablo on 19-12-13.
 */
public class MensajesApoderado extends ActionBarActivity {

    String user;
    String user_alumno;
    String user_profesor;
    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String token;
    String URL_connect= con.URL_connect;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes_apoderado);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user_profesor  = extras.getString("user_profesor");//usuario
            user_alumno=extras.getString("user_alumno");
            token  = extras.getString("token");//usuario

            new asincrona().execute(user);
        }
        else
        {
            user="error";
            user_alumno="error";
            user_profesor="error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    public ArrayList<Entidad_mensajes_apoderado> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_mensajes_apoderado> array_mensajes_apoderado = new ArrayList<Entidad_mensajes_apoderado>();
        Entidad_mensajes_apoderado mensajes_apoderado;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "mensajes_profesor"));
        postparameterssend.add(new BasicNameValuePair("user_id_from",user_profesor));
        postparameterssend.add(new BasicNameValuePair("user_id_to",user_alumno));
        postparameterssend.add(new BasicNameValuePair("token",token));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase mensajes_apoderado", "cadena de envio mensajes_apoderado:" + URL_connect + postparameterssend);
        Log.e("Clase mensajes_apoderado", "cadena de regreso mensajes_apoderado:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("id").equals("error_sesion"))
                {
                    Intent i=new Intent(MensajesApoderado.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                else
                {
                    for (int i=0; i<jdata.length();i++)
                    {

                        mensajes_apoderado = new Entidad_mensajes_apoderado(getResources().getDrawable(R.drawable.mensaje_leido), jdata.getJSONObject(i).getString("useridfrom"), jdata.getJSONObject(i).getString("useridto"), jdata.getJSONObject(i).getString("subject"),jdata.getJSONObject(i).getString("fullmessage"),jdata.getJSONObject(i).getString("smallmessage"),jdata.getJSONObject(i).getString("fecha_creacion"));
                        array_mensajes_apoderado.add(mensajes_apoderado);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                mensajes_apoderado = new Entidad_mensajes_apoderado(getResources().getDrawable(R.drawable.icono_interrogracion)," ? "," ? "," ?   "," ? "," ? "," ? ");
                array_mensajes_apoderado.add(mensajes_apoderado);

            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se han encontrado mensajes", Toast.LENGTH_SHORT);
            toast1.show();
        }
        Log.e("Clase mensajes", "Array de mensajes" + array_mensajes_apoderado);
        return array_mensajes_apoderado;
    }
    public void cargaListado(ArrayList<Entidad_mensajes_apoderado> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listamensajesapoderado);
        Adapter_mensajes_apoderado adapter = new Adapter_mensajes_apoderado(this, datos);
        /*listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
            {
                //Se coloca la accion a hacer al seleccionar elemento
            }
        });*/
        lista.setAdapter(adapter);
    }
    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_mensajes_apoderado>>
    {
        String user;
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(MensajesApoderado.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_mensajes_apoderado> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
                return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_mensajes_apoderado> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_mensajes_apoderado> cadena = result;
                cargaListado(cadena);
            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener los mensajes que ya se leyeron", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(MensajesApoderado.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
