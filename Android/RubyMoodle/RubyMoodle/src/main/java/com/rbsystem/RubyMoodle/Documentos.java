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
import entidad.Entidad_documentos;
import library.Adapter_documentos;
import library.Httppostaux;

import java.util.ArrayList;

/**
 * Created by josépablo on 19-12-13.
 */
public class Documentos extends ActionBarActivity {

    String user;
    String user_id;
    String asignatura;
    String token;
    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;

    //este objeto se ocupa para las variables porcentaje_presente, dias presente, dias ausente, dias ausente justificado
    Entidad_documentos edo = new Entidad_documentos();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user  = extras.getString("user");//usuario
            user_id = extras.getString("user_id");
            asignatura = extras.getString("curso");
            token =extras.getString("token");
            new asincrona().execute(user,asignatura);
        }
        else
        {
            user="error";
            user_id="error";
            asignatura = "error";
            token ="error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
        // porcentajepresente.setText("bien");
    }
    public ArrayList<Entidad_documentos> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_documentos> array_documentos = new ArrayList<Entidad_documentos>();
        Entidad_documentos documentos;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "documentos_curso"));
        postparameterssend.add(new BasicNameValuePair("username",asignatura));
        postparameterssend.add(new BasicNameValuePair("token",token));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase documentos", "cadena de envio documentos:" + URL_connect + postparameterssend);
        Log.e("Clase documentos", "cadena de regreso documentos:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("id").equals("error_sesion"))
                {
                    Intent i=new Intent(Documentos.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }

                for (int i=0; i<jdata.length();i++)
                {
                         documentos = new Entidad_documentos(getResources().getDrawable(R.drawable.icono_documentos),jdata.getJSONObject(i).getInt("id"),jdata.getJSONObject(i).getInt("category"),jdata.getJSONObject(i).getString("fullname"),jdata.getJSONObject(i).getString("id_curso"),jdata.getJSONObject(i).getString("name"),jdata.getJSONObject(i).getString("disponible_desde"));
                    array_documentos.add(documentos);
                }

            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                documentos = new Entidad_documentos(getResources().getDrawable(R.drawable.icono_interrogracion),0,0," ?   "," ? "," ? "," ? ");
                array_documentos.add(documentos);

            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se ha encontrado documentos", Toast.LENGTH_SHORT);
            toast1.show();
        }
        return array_documentos;
    }

    public void cargaListado(ArrayList<Entidad_documentos> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listadocumentos);
        Adapter_documentos adapter = new Adapter_documentos(this, datos);
        lista.setAdapter(adapter);
    }


    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_documentos>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(Documentos.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_documentos> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
           return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_documentos> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_documentos> cadena = result;
                cargaListado(cadena);
            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener el profesor del curso", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(Documentos.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
