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
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import AccesoDatos.Conectar;
import entidad.Entidad_participantes;
import library.Adapter_participantes;
import library.Httppostaux;

import java.util.ArrayList;

/**
 * Created by josépablo on 19-12-13.
 */
public class Participantes extends ActionBarActivity {

    String user;
    String user_id;
    String curso;
    String token;
    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);
        Bundle extras = getIntent().getExtras();

        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user  = extras.getString("user");//usuario
            user_id = extras.getString("user_id");
            curso = extras.getString("curso");
            token = extras.getString("token");

            new asincrona().execute(user,curso);
        }
        else
        {
            user="error";
            user_id = "error";
            curso ="curso";
            token = "error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    public ArrayList<Entidad_participantes> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_participantes> array_participantes = new ArrayList<Entidad_participantes>();
        Entidad_participantes participantes;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "participantes_curso"));
        postparameterssend.add(new BasicNameValuePair("username",curso));
        postparameterssend.add(new BasicNameValuePair("token",token));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase participantes", "cadena de envio participantes:" + URL_connect + postparameterssend);
        Log.e("Clase participantes", "cadena de regreso participantes:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("id").equals("error_sesion"))
                {
                    Intent i=new Intent(Participantes.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                else
                {
                    for (int i=0; i<jdata.length();i++)
                    {

                        participantes = new Entidad_participantes(getResources().getDrawable(R.drawable.icono_alumno), jdata.getJSONObject(i).getString("id"), jdata.getJSONObject(i).getString("username"), jdata.getJSONObject(i).getString("firstname"),jdata.getJSONObject(i).getString("lastname"),jdata.getJSONObject(i).getString("email"));
                        array_participantes.add(participantes);
                    }
                }
            }
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error de conexión al obtener los mensajes", Toast.LENGTH_SHORT);
                toast1.show();
            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se han encontrado participantes", Toast.LENGTH_SHORT);
            toast1.show();
        }
        Log.e("Clase participantes", "Array de participantes" + array_participantes);
        return array_participantes;
    }
    public void cargaListado(ArrayList<Entidad_participantes> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listaparticipantes);
        Adapter_participantes adapter = new Adapter_participantes(this, datos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Entidad_participantes item= (Entidad_participantes)adapterView.getAdapter().getItem(i);
                Log.e("Clase Participantes","useridfrom: "+user_id);
                Log.e("Clase Participantes","useridto: "+item.getId_participante());
                Log.e("Clase Participantes","user: " + user);
                Log.e("Clase Participantes","token: " + token);

                Intent in=new Intent(Participantes.this, EnviarMensajeParticipante.class);
                in.putExtra("user",user);
                in.putExtra("user_id_to",item.getId_participante());
                in.putExtra("user_id_from",user_id);
                in.putExtra("token",token);


                startActivity(in);
                //Toast.makeText(getBaseContext(),item.getFecha_creacion() , Toast.LENGTH_SHORT).show();
            }
        });
        lista.setAdapter(adapter);
    }
    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_participantes>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(Participantes.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_participantes> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
            return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_participantes> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_participantes> cadena = result;
                cargaListado(cadena);
            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener los participantes", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(Participantes.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
