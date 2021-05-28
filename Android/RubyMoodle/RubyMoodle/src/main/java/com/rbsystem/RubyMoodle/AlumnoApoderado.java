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

import java.util.ArrayList;

import AccesoDatos.Conectar;
import entidad.Entidad_alumno_apoderado;
import library.Adapter_alumno_apoderado;
import library.Httppostaux;

/**
 * Created by josépablo on 19-12-13.
 */
public class AlumnoApoderado extends ActionBarActivity {

    String user;
    String token;
    String user_id;
    String nombre_apoderado;
    String email;
    String rut;
    int logstatus;
    int token_a_enviar;
    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno_apoderado);
        Bundle extras = getIntent().getExtras();

        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user  = extras.getString("user");//usuario
            token = Integer.toString(extras.getInt("token"));
            user_id = extras.getString("user_id");
            logstatus =  extras.getInt("logstatus");
            token_a_enviar = extras.getInt("token");
            nombre_apoderado = extras.getString("nombre");
            rut = extras.getString("rut");
            email= extras.getString("email");
            new asincrona().execute(user);
        }
        else
        {
            user="error";
            token="error";
            user_id="error";
            logstatus=0;
            token_a_enviar=0;
            nombre_apoderado="error";
            rut = "error";
            email = "error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
    }
    public ArrayList<Entidad_alumno_apoderado> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_alumno_apoderado> array_alumno_apoderado = new ArrayList<Entidad_alumno_apoderado>();
        Entidad_alumno_apoderado alumno_apoderado;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "alumno_apoderado"));
        postparameterssend.add(new BasicNameValuePair("username",user));
        postparameterssend.add(new BasicNameValuePair("token",token));


        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase alumnoApoderado", "cadena de envio alumnoApoderado:" + URL_connect + postparameterssend);
        Log.e("Clase alumnoApoderado", "cadena de regreso alumnoApoderado:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("RUT").equals("error_sesion"))
                {
                    Intent i=new Intent(AlumnoApoderado.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                else
                {
                    for (int i=0; i<jdata.length();i++)
                    {
                        alumno_apoderado = new Entidad_alumno_apoderado(getResources().getDrawable(R.drawable.icono_alumno), jdata.getJSONObject(i).getString("RUT"), jdata.getJSONObject(i).getString("USER_ID"), jdata.getJSONObject(i).getString("NOMBRE"), jdata.getJSONObject(i).getString("APELLIDO"), jdata.getJSONObject(i).getString("email"));
                        array_alumno_apoderado.add(alumno_apoderado);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                alumno_apoderado = new Entidad_alumno_apoderado(getResources().getDrawable(R.drawable.icono_interrogracion)," ?   "," ?  "," ?  "," ?  "," ?  ");
                array_alumno_apoderado.add(alumno_apoderado);
            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se han encontrado cursos", Toast.LENGTH_SHORT);
            toast1.show();
        }
        Log.e("Clase alumnoApoderado", "Array de alumnoApoderado" + array_alumno_apoderado);
        return array_alumno_apoderado;
    }
    public void cargaListado(ArrayList<Entidad_alumno_apoderado> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listaalumnoapoderado);
        Adapter_alumno_apoderado adapter = new Adapter_alumno_apoderado(this, datos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Entidad_alumno_apoderado item= (Entidad_alumno_apoderado)adapterView.getAdapter().getItem(i);
                Log.e("Clase alumnoApoderado","rut"+item.getRut());
                Log.e("Clase alumnoApoderado","user_apoderado: " + user);
                Log.e("Clase alumnoApoderado","token: " + token);
                Log.e("Clase alumnoApoderado","user_id: " + user_id);
                Log.e("Clase alumnoApoderado","logstatus: " + logstatus);
                Log.e("Clase alumnoApoderado","nombre_alumno: " + item.getNombre());
                Log.e("Clase alumnoApoderado","apellido_alumno: " + item.getApellido());
                Log.e("Clase alumnoApoderado","email_alumno: " + item.getEmail());




                Intent in=new Intent(AlumnoApoderado.this, MenuPrincipal.class);
                in.putExtra("user",item.getRut());
                in.putExtra("nombre","Apoderado "+nombre_apoderado);
                in.putExtra("logstatus",logstatus);
                in.putExtra("user_id",item.getUser_id());
                in.putExtra("token",token_a_enviar);
                in.putExtra("rut",rut);
                in.putExtra("email",email);

                startActivity(in);
                //Toast.makeText(getBaseContext(),item.getFecha_creacion() , Toast.LENGTH_SHORT).show();
            }
        });
        lista.setAdapter(adapter);
    }
    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_alumno_apoderado>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(AlumnoApoderado.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_alumno_apoderado> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
            return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_alumno_apoderado> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_alumno_apoderado> cadena = result;
                cargaListado(cadena);
            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener los cursos", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(AlumnoApoderado.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
