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
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import AccesoDatos.Conectar;
import entidad.Entidad_mensajes;
import library.Adapter_mensajes;
import library.Httppostaux;

import java.util.ArrayList;

/**
 * Created by josépablo on 19-12-13.
 */
public class Mensajes extends ActionBarActivity {

    String user;
    ImageButton misMensajesLeidos;
    private ProgressDialog pDialog;
    String token;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajes);
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
            token ="error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    public ArrayList<Entidad_mensajes> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_mensajes> array_mensajes = new ArrayList<Entidad_mensajes>();
        Entidad_mensajes mensajes;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "mensajes_sin_leer"));
        postparameterssend.add(new BasicNameValuePair("username",user));
        postparameterssend.add(new BasicNameValuePair("token",token));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase mensajes", "cadena de envio mensajes:" + URL_connect + postparameterssend);
        Log.e("Clase mensajes", "cadena de regreso mensajes:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("id").equals("error_sesion"))
                {
                    Intent i=new Intent(Mensajes.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                else
                {
                    for (int i=0; i<jdata.length();i++)
                    {

                        mensajes = new Entidad_mensajes(getResources().getDrawable(R.drawable.correo), jdata.getJSONObject(i).getString("useridfrom"), jdata.getJSONObject(i).getString("useridto"), jdata.getJSONObject(i).getString("subject"),jdata.getJSONObject(i).getString("fullmessage"),jdata.getJSONObject(i).getString("smallmessage"),jdata.getJSONObject(i).getString("fecha_creacion"),jdata.getJSONObject(i).getInt("id"));
                        array_mensajes.add(mensajes);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                mensajes = new Entidad_mensajes(getResources().getDrawable(R.drawable.icono_interrogracion)," ? ", " ? "," ? "," ? "," ? "," ? ", 1);
                array_mensajes.add(mensajes);
            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se han encontrado mensajes", Toast.LENGTH_SHORT);
            toast1.show();
        }
        Log.e("Clase mensajes", "Array de mensajes" + array_mensajes);
        return array_mensajes;
    }
    public void cargaListado(ArrayList<Entidad_mensajes> datos)
    {
         ListView lista = (ListView) findViewById(R.id.listamensajes);
         Adapter_mensajes adapter = new Adapter_mensajes(this, datos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Entidad_mensajes item= (Entidad_mensajes)adapterView.getAdapter().getItem(i);
                Log.e("Clase mensajes","fecha creacion"+item.getFecha_creacion());
                Log.e("Clase mensajes","smallmessage"+item.getSmallmessage());
                Log.e("Clase mensajes","useridfrom"+item.getUseridfrom());
                Log.e("Clase mensajea","useridto"+item.getUseridto());
                Log.e("Clase mensajes","user: " + user);
                Log.e("Clase mensajes","id_mensaje: " + item.getId_mensaje());
                Log.e("Clase mensajes","token: " + token);


                Intent in=new Intent(Mensajes.this, EnviarMensaje.class);
                in.putExtra("user",user);
                in.putExtra("user_id_to",item.getUseridto());
                in.putExtra("user_id_from",item.getUseridfrom());
                in.putExtra("smallmessage",item.getSmallmessage());
                in.putExtra("fecha_creacion", item.getFecha_creacion());
                in.putExtra("id_mensaje", item.getId_mensaje());
                in.putExtra("token", token);

                startActivity(in);
                //Toast.makeText(getBaseContext(),item.getFecha_creacion() , Toast.LENGTH_SHORT).show();
            }
        });
        lista.setAdapter(adapter);
    }
    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_mensajes>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(Mensajes.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_mensajes> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
                return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_mensajes> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_mensajes> cadena = result;
                cargaListado(cadena);

                misMensajesLeidos = (ImageButton) findViewById(R.id.mismensajesleidos);
                misMensajesLeidos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0)
                    {
                        //lo que queremos que pase cuando se pulse
                        Intent i=new Intent(Mensajes.this, MensajesLeidos.class);
                        i.putExtra("user",user);
                        i.putExtra("token",token);

                        Log.e("Clase mensajes", "user" + user);

                        startActivity(i);
                    }
                });

            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener los mensajes", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(Mensajes.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
