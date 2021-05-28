package com.rbsystem.RubyMoodle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import AccesoDatos.Conectar;
import entidad.Entidad_eventos;
import library.Adapter_eventos;
import library.Httppostaux;

import java.util.ArrayList;

/**
 * Created by josépablo on 19-12-13.
 */
public class Eventos extends ActionBarActivity {

    String user;
    String token;
    ImageButton misEventosAtrasados;

    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;

    //este objeto se ocupa para las variables porcentaje_presente, dias presente, dias ausente, dias ausente justificado
    Entidad_eventos ee = new Entidad_eventos();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user  = extras.getString("user");//usuario
            token = Integer.toString(extras.getInt("token"));
            new asincrona().execute(user);
        }
        else
        {
            user="error";
            token ="error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
        // porcentajepresente.setText("bien");
    }
    public ArrayList<Entidad_eventos> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_eventos> array_eventos = new ArrayList<Entidad_eventos>();
        Entidad_eventos eventos;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "eventos"));
        postparameterssend.add(new BasicNameValuePair("username",user));
        postparameterssend.add(new BasicNameValuePair("token",token));


        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase eventos", "cadena de envio eventos:" + URL_connect + postparameterssend);
        Log.e("Clase eventos", "cadena de regreso eventos:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("fullname").equals("error_sesion"))
                {
                    Intent i=new Intent(Eventos.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                else
                {
                   for (int i=0; i<jdata.length();i++)
                    {
                         eventos = new Entidad_eventos(getResources().getDrawable(R.drawable.icono_actividades),jdata.getJSONObject(i).getString("fullname"),jdata.getJSONObject(i).getString("desde"),jdata.getJSONObject(i).getString("hasta"),jdata.getJSONObject(i).getString("name"));

                        array_eventos.add(eventos);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                eventos = new Entidad_eventos(getResources().getDrawable(R.drawable.icono_interrogracion)," ?   ","   ?    "," ?   "," ?  ");
                array_eventos.add(eventos);
            }
        }
        else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se ha encontrado eventos", Toast.LENGTH_SHORT);
            toast1.show();
        }

        Log.e("Clase eventos", "Array de eventos" + array_eventos);
        return array_eventos;
    }
    public void cargaListado(ArrayList<Entidad_eventos> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listaeventos);
        Adapter_eventos adapter = new Adapter_eventos(this, datos);

        lista.setAdapter(adapter);
    }
    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_eventos>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(Eventos.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_eventos> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
            return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_eventos> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_eventos> cadena = result;
                cargaListado(cadena);

                misEventosAtrasados = (ImageButton) findViewById(R.id.misEventosAtrasados);
                misEventosAtrasados.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0)
                    {
                        //lo que queremos que pase cuando se pulse
                        Intent i=new Intent(Eventos.this, EventosAtrasados.class);
                        i.putExtra("user",user);
                        i.putExtra("token",token);

                        startActivity(i);
                    }
                });

            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                //Este catch cae por que no encuentra sesion del usuario, y el metodo obtDatosJson envia un array que no corresponde a carga listado
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                Toast toast1 = Toast.makeText(getApplicationContext(),"El usuario no tiene actividades", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }
    }
}
