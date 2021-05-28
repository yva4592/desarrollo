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
import entidad.Entidad_documentos;
import entidad.Entidad_mensajes_leidos;
import library.Adapter_mensajes_leidos;
import library.Httppostaux;

/**
 * Created by josépablo on 19-12-13.
 */
public class MensajesLeidos extends ActionBarActivity {

    String user;
    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String token;
    String URL_connect= con.URL_connect;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes_leidos);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user  = extras.getString("user");//usuario
            token  = extras.getString("token");//usuario

            new asincrona().execute(user);
        }
        else
        {
            user="error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    public ArrayList<Entidad_mensajes_leidos> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_mensajes_leidos> array_mensajes_leidos = new ArrayList<Entidad_mensajes_leidos>();
        Entidad_mensajes_leidos mensajes_leidos;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "ver_mensajes_leidos"));
        postparameterssend.add(new BasicNameValuePair("username",user));
        postparameterssend.add(new BasicNameValuePair("token",token));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase mensajes_leidos", "cadena de envio mensajes_leidos:" + URL_connect + postparameterssend);
        Log.e("Clase mensajes_leidos", "cadena de regreso mensajes_leios:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("id").equals("error_sesion"))
                {
                    Intent i=new Intent(MensajesLeidos.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                else
                {
                    for (int i=0; i<jdata.length();i++)
                    {

                        mensajes_leidos = new Entidad_mensajes_leidos(getResources().getDrawable(R.drawable.mensaje_leido), jdata.getJSONObject(i).getString("useridfrom"), jdata.getJSONObject(i).getString("useridto"), jdata.getJSONObject(i).getString("subject"),jdata.getJSONObject(i).getString("fullmessage"),jdata.getJSONObject(i).getString("smallmessage"),jdata.getJSONObject(i).getString("creado_el"),jdata.getJSONObject(i).getString("leido_el"));
                        array_mensajes_leidos.add(mensajes_leidos);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                mensajes_leidos = new Entidad_mensajes_leidos(getResources().getDrawable(R.drawable.icono_interrogracion)," ? "," ? "," ?   "," ? "," ? "," ? "," ? ");
                array_mensajes_leidos.add(mensajes_leidos);

            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se han encontrado mensajes", Toast.LENGTH_SHORT);
            toast1.show();
        }
        Log.e("Clase mensajes", "Array de mensajes" + array_mensajes_leidos);
        return array_mensajes_leidos;
    }
    public void cargaListado(ArrayList<Entidad_mensajes_leidos> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listamensajesleidos);
        Adapter_mensajes_leidos adapter = new Adapter_mensajes_leidos(this, datos);
        /*listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
            {
                //Se coloca la accion a hacer al seleccionar elemento
            }
        });*/
        lista.setAdapter(adapter);
    }
    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_mensajes_leidos>>
    {
        String user;
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(MensajesLeidos.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_mensajes_leidos> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
                return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_mensajes_leidos> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_mensajes_leidos> cadena = result;
                cargaListado(cadena);
            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener los mensajes que ya se leyeron", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(MensajesLeidos.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
