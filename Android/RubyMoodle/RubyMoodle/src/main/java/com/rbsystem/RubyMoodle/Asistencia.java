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
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import AccesoDatos.Conectar;
import entidad.Entidad_asistencia;
import library.Adapter_asistencia;
import library.Httppostaux;

import java.util.ArrayList;

/**
 * Created by josépablo on 19-12-13.
 */
public class Asistencia extends ActionBarActivity {

    String user;
    String token;
   // TextView porcentajepresente;
    TextView diaspresente;
    TextView diasausente;
    TextView diasausentejustificado;

    private ProgressDialog pDialog;
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;

    //este objeto se ocupa para las variables porcentaje_presente, dias presente, dias ausente, dias ausente justificado
    Entidad_asistencia ea = new Entidad_asistencia();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia);
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
    public ArrayList<Entidad_asistencia> obtDatosJSON()
    {
        Httppostaux post;
        post=new Httppostaux();
        ArrayList<Entidad_asistencia> array_asistencia = new ArrayList<Entidad_asistencia>();
        Entidad_asistencia asistencia;

        ArrayList<NameValuePair> postparameterssend= new ArrayList<NameValuePair>();
        postparameterssend.add(new BasicNameValuePair("opcion", "asistencia"));
        postparameterssend.add(new BasicNameValuePair("username",user));
        postparameterssend.add(new BasicNameValuePair("token",token));


        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameterssend, URL_connect);
        Log.e("Clase asistencia", "cadena de envio asistencia:" + URL_connect + postparameterssend);
        Log.e("Clase asistencia", "cadena de regreso asistencia:" + jdata);

        if (jdata!=null && jdata.length() > 0)
        {
            // Introduzco los datos
            try{
                 if(jdata.getJSONObject(0).getString("porcentaje_presente").equals("error_sesion"))
                {
                        Intent i=new Intent(Asistencia.this, MainActivity.class);
                        startActivity(i);
                        System.exit(0);
                }
                else
                {
                   //ea.porcentaje_presente = jdata.getJSONObject(0).getString("porcentaje_presente");
                   ea.dias_presentes = jdata.getJSONObject(1).getString("dias_presentes");
                   ea.dias_ausentes = jdata.getJSONObject(2).getString("dias_ausente");
                   ea.dias_ausentes_justificado = jdata.getJSONObject(3).getString("dias_ausente_justificado");
                    for (int i=4; i<jdata.length();i++)
                    {
                        if(jdata.getJSONObject(i).getString("estado").equals("Presente")){
                        asistencia = new Entidad_asistencia(getResources().getDrawable(R.drawable.icono_aceptar),jdata.getJSONObject(i).getString("fecha"),jdata.getJSONObject(i).getString("estado"));
                        }
                        else if(jdata.getJSONObject(i).getString("estado").equals("Ausente") ){
                            asistencia = new Entidad_asistencia(getResources().getDrawable(R.drawable.icono_rechazar),jdata.getJSONObject(i).getString("fecha"),jdata.getJSONObject(i).getString("estado"));
                        }
                        else{
                            asistencia = new Entidad_asistencia(getResources().getDrawable(R.drawable.icono_ausente_justificado),jdata.getJSONObject(i).getString("fecha"),jdata.getJSONObject(i).getString("estado"));
                        }
                        array_asistencia.add(asistencia);
                    }
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                ea.porcentaje_presente = " ? ";
                ea.dias_presentes = " ? ";
                ea.dias_ausentes = " ? ";
                ea.dias_ausentes_justificado = " ? ";
                asistencia = new Entidad_asistencia(getResources().getDrawable(R.drawable.icono_interrogracion)," ?   ","   ?   ");
                array_asistencia.add(asistencia);
            }
        }
        else{
            Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(400);
            Toast toast1 = Toast.makeText(getApplicationContext(),"No se ha encontrado asistencia", Toast.LENGTH_SHORT);
            toast1.show();
        }


        Log.e("Clase asistencia", "Array de asistencia" + array_asistencia);
        return array_asistencia;
    }
    public void cargaListado(ArrayList<Entidad_asistencia> datos)
    {
        ListView lista = (ListView) findViewById(R.id.listaasistencia);
        Adapter_asistencia adapter = new Adapter_asistencia(this, datos);

        //porcentajepresente= (TextView) findViewById(R.id.porcentajepresente);
        diaspresente= (TextView) findViewById(R.id.diaspresente);
        diasausente= (TextView) findViewById(R.id.diasausente);
        diasausentejustificado= (TextView) findViewById(R.id.diasausentejustificado);

        //porcentajepresente.setText("  "+ea.getPorcentaje_presente().substring(0,2)+"%  ");
        diaspresente.setText("  "+ea.getDias_presentes()+"  ");
        diasausente.setText("  "+ea.getDias_ausentes()+"  ");
        diasausentejustificado.setText("  "+ea.getDias_ausentes_justificado()+"  ");

        lista.setAdapter(adapter);
    }
    class asincrona extends AsyncTask<String, String, ArrayList<Entidad_asistencia>>
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(Asistencia.this);
            pDialog.setMessage("Un momento....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        protected ArrayList<Entidad_asistencia> doInBackground(String... params)
        {
            //Ejecuto el metodo obtDatosJSON(), y el return de este metodo, lo retorno otra vez
            return obtDatosJSON();
        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a onPostExecute o mostramos error*/
        protected void onPostExecute(ArrayList<Entidad_asistencia> result)
        {
            //escondemos el mensaje de espera
            pDialog.dismiss();
            // si no hay errores, cargamos los datos obtenidos en obtDatosJSON, a la lista
            try
            {
                ArrayList<Entidad_asistencia> cadena = result;
                cargaListado(cadena);


            }
            //si hay un error con el usuario devuelve a menu principal
            catch (Exception e)
            {
                //Este catch cae por que no encuentra sesion del usuario, y el metodo obtDatosJson envia un array que no corresponde a carga listado
                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(400);
                Toast toast1 = Toast.makeText(getApplicationContext(),"El usuario no tiene asistencia", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }
    }
}
