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
import org.json.JSONException;

import AccesoDatos.Conectar;
import entidad.Entidad_cursos;
import library.Adapter_cursos;
import library.Httppostaux;

import java.util.ArrayList;

/**
 * Created by josépablo on 19-12-13.
 */
public class Cursos extends ActionBarActivity {

    String user;
    String token;
    String user_id;
    String logstatus;
    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);
        Bundle extras = getIntent().getExtras();

        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user  = extras.getString("user");//usuario
            token = Integer.toString(extras.getInt("token"));
            user_id = extras.getString("user_id");
            logstatus = extras.getString("logstatus");
            new asincrona().execute(user);
        }
        else
        {
            user="error";
            token="error";
            user_id="error";
            logstatus = "error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    public ArrayList<Entidad_cursos> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_cursos> array_cursos = new ArrayList<Entidad_cursos>();
        Entidad_cursos cursos;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "cursos"));
        postparameterssend.add(new BasicNameValuePair("username",user));
        postparameterssend.add(new BasicNameValuePair("token",token));


        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase cursos", "cadena de envio cursos:" + URL_connect + postparameterssend);
        Log.e("Clase cursos", "cadena de regreso cursos:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("fullname").equals("error_sesion"))
                {
                    Intent i=new Intent(Cursos.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                else
                {
                    for (int i=0; i<jdata.length();i++)
                    {
                        cursos = new Entidad_cursos(getResources().getDrawable(R.drawable.icono_asignaturas), jdata.getJSONObject(i).getString("fullname"));
                        array_cursos.add(cursos);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                cursos = new Entidad_cursos(getResources().getDrawable(R.drawable.icono_interrogracion)," ?   ");
                array_cursos.add(cursos);
            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se han encontrado cursos", Toast.LENGTH_SHORT);
            toast1.show();
        }
        Log.e("Clase cursos", "Array de cursos" + array_cursos);
        return array_cursos;
    }
    public void cargaListado(ArrayList<Entidad_cursos> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listacursos);
        Adapter_cursos adapter = new Adapter_cursos(this, datos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Entidad_cursos item= (Entidad_cursos)adapterView.getAdapter().getItem(i);
                Log.e("Clase cursos","asignatura"+item.getAsignatura());
                Log.e("Clase cursos","user: " + user);
                Log.e("Clase cursos","token: " + token);
                Log.e("Clase cursos","user_id: " + user_id);
                Log.e("Clase cursos","logstatus: " + logstatus);

                if(logstatus.equals("1")){
                Intent in=new Intent(Cursos.this, MenuCursos.class);
                in.putExtra("user",user);
                in.putExtra("curso",item.getAsignatura());
                in.putExtra("user_id",user_id);
                in.putExtra("token",token);
                in.putExtra("logstatus",logstatus);

                startActivity(in);
                //Toast.makeText(getBaseContext(),item.getFecha_creacion() , Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent in=new Intent(Cursos.this, MenuCursosApoderados.class);
                    in.putExtra("user",user);
                    in.putExtra("curso",item.getAsignatura());
                    in.putExtra("user_id",user_id);
                    in.putExtra("token",token);
                    in.putExtra("logstatus",logstatus);

                    startActivity(in);
                    //Toast.makeText(getBaseContext(),item.getFecha_creacion() , Toast.LENGTH_SHORT).show();
                }
            }
        });
        lista.setAdapter(adapter);
    }
    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_cursos>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(Cursos.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_cursos> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
            return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_cursos> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_cursos> cadena = result;
                cargaListado(cadena);
            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener los cursos", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(Cursos.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
