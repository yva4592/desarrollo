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
import entidad.Entidad_profesor;
import library.Adapter_profesor;
import library.Httppostaux;

import java.util.ArrayList;

/**
 * Created by josépablo on 19-12-13.
 */
public class Profesor extends ActionBarActivity {

    String user;
    String curso;
    String user_id;
    String token;
    String logstatus;
    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;

    //este objeto se ocupa para las variables porcentaje_presente, dias presente, dias ausente, dias ausente justificado
    Entidad_profesor epr = new Entidad_profesor();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
        if (extras != null)
        {
            user  = extras.getString("user");//usuario
            curso = extras.getString("curso");//curso
            user_id = extras.getString("user_id");
            token = extras.getString("token");
            logstatus = extras.getString("logstatus");
            new asincrona().execute(user,curso);
        }
        else
        {
            user="error";
            curso="error";
            user_id="error";
            token ="error";
            logstatus="error";
            //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(200);
            Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener al usuario", Toast.LENGTH_SHORT);
            toast1.show();
        }
        // porcentajepresente.setText("bien");
    }
    public ArrayList<Entidad_profesor> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_profesor> array_profesor = new ArrayList<Entidad_profesor>();
        Entidad_profesor profesor;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "profesor_del_curso"));
        postparameterssend.add(new BasicNameValuePair("username",curso));
        postparameterssend.add(new BasicNameValuePair("token",token));

        //realizamos una peticion y como respuesta obtenes un array JSON

        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase profesor", "cadena de envio profesor:" + URL_connect + postparameterssend);
        Log.e("Clase profesor", "cadena de regreso profesor:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                if(jdata.getJSONObject(0).getString("id").equals("error_sesion"))
                {
                    Intent i=new Intent(Profesor.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }
                else
                {
                    for (int i=0; i<jdata.length();i++)
                    {

                        //profesor = new Entidad_profesor(getResources().getDrawable(R.drawable.icono_profesor),jdata.getJSONObject(i).getString("id"),jdata.getJSONObject(i).getString("username"),jdata.getJSONObject(i).getString("firstname"),jdata.getJSONObject(i).getString("lastname"),jdata.getJSONObject(i).getString("email"));
                        profesor = new Entidad_profesor(getResources().getDrawable(R.drawable.icono_profesor),jdata.getJSONObject(i).getString("id"),jdata.getJSONObject(i).getString("username"),jdata.getJSONObject(i).getString("firstname"),jdata.getJSONObject(i).getString("lastname"),jdata.getJSONObject(i).getString("email"));
                        array_profesor.add(profesor);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                profesor = new Entidad_profesor(getResources().getDrawable(R.drawable.icono_interrogracion)," ?   ","   ?    "," ?   "," ?  "," ?  ");
                array_profesor.add(profesor);
            }

        }else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se ha encontrado profesor asignado", Toast.LENGTH_SHORT);
            toast1.show();


        }

        return array_profesor;

    }
    public void cargaListado(ArrayList<Entidad_profesor> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listaprofesor);
        Adapter_profesor adapter = new Adapter_profesor(this, datos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Entidad_profesor item= (Entidad_profesor)adapterView.getAdapter().getItem(i);
                Log.e("Clase profesor","useridfrom: "+user_id);
                Log.e("Clase profesor","useridto: "+item.getUser_id_to());
                Log.e("Clase profesor","user: " + user);
                Log.e("Clase profesor","token: " + token);
                Log.e("Clase profesor","logstatus: " + logstatus);

                if(logstatus.equals("1")){
                Intent in=new Intent(Profesor.this, EnviarMensajeProfesor.class);
                in.putExtra("user",user);
                in.putExtra("user_id_to",item.getUser_id_to());
                in.putExtra("user_id_from",user_id);
                in.putExtra("token",token);

                startActivity(in);
                //Toast.makeText(getBaseContext(),item.getFecha_creacion() , Toast.LENGTH_SHORT).show();
                }else{
                    Intent in=new Intent(Profesor.this, EnviarMensajeProfesorApoderado.class);
                    in.putExtra("user",user);
                    in.putExtra("user_id_to",item.getUser_id_to());
                    in.putExtra("user_id_from",user_id);
                    in.putExtra("token",token);

                    startActivity(in);
                }
            }
        });
        lista.setAdapter(adapter);
    }


    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_profesor>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(Profesor.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_profesor> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
            return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_profesor> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_profesor> cadena = result;
                cargaListado(cadena);
            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(200);
                Toast toast1 = Toast.makeText(getApplicationContext(),"Error al obtener el profesor del curso", Toast.LENGTH_SHORT);
                toast1.show();
                Intent i=new Intent(Profesor.this, MainActivity.class);
                startActivity(i);
            }
        }
    }
}
