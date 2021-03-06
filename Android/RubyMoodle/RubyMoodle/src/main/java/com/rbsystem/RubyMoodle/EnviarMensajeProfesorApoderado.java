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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import AccesoDatos.Conectar;
import entidad.Entidad_enviar_mensaje;
import library.Httppostaux;


public class EnviarMensajeProfesorApoderado extends ActionBarActivity
{
    EditText mensaje_a_enviar;
    Button enviarmensaje;
    Httppostaux post;
    String token;
    ImageButton misMensajes;
    private ProgressDialog pDialog;

    //llamada a variables clase conectar
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;
    Entidad_enviar_mensaje enviar_mensaje = new Entidad_enviar_mensaje();



    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_mensaje_profesor_apoderado);
        post=new Httppostaux();

        mensaje_a_enviar= (EditText) findViewById(R.id.mensaje);
        enviarmensaje= (Button) findViewById(R.id.enviarmensaje);

        //Obtenemos datos enviados en el intent.
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {

            enviar_mensaje.user = (extras.getString("user"));//usuario
            enviar_mensaje.user_id_to  = extras.getString("user_id_to");//user quien recibio el mensaje
            enviar_mensaje.user_id_from = extras.getString("user_id_from"); //user de quien es el mensaje
            token = extras.getString("token");
            Log.e("Clase EnviarMensajeProfesorApoderado","useridfrom"+enviar_mensaje.getUser_id_from());
            Log.e("Clase EnviarMensajeProfesorApoderado","useridto"+enviar_mensaje.getUser_id_to());
            Log.e("Clase EnviarMensajeProfesorApoderado","user: " + enviar_mensaje.getUser());
            Log.e("Clase EnviarMensajeProfesorApoderado","token: " + token);

        }
        else
        {
            enviar_mensaje.user = "error";
            enviar_mensaje.user_id_to="error";
            enviar_mensaje.user_id_from="error";
            token = "error";
        }
        //accion al momento de presionar el boton login
        enviarmensaje.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                //Extraccion de  datos de los EditText
                enviar_mensaje.mensaje=mensaje_a_enviar.getText().toString();
                //se comprueba  si estan en blanco
                //si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
                if( checklogindata( enviar_mensaje.getMensaje()) ==true)
                {
                    new asynclogin().execute(enviar_mensaje.getMensaje());
                }
                //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
                else
                {
                    err_login();
                }
            }
        });
        misMensajes = (ImageButton) findViewById(R.id.misMensajes);
        misMensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0)
            {
                //lo que queremos que pase cuando se pulse
                Intent i=new Intent(EnviarMensajeProfesorApoderado.this, MensajesApoderado.class);
                i.putExtra("user_profesor",enviar_mensaje.getUser_id_to());
                i.putExtra("user_alumno",enviar_mensaje.getUser_id_from());
                i.putExtra("token",token);

                Log.e("Clase EnviarMensajeProfesorApoderado","user_profesor"+enviar_mensaje.getUser_id_to());
                Log.e("Clase EnviarMensajeProfesorApoderado","user_alumno: " + enviar_mensaje.getUser_id_from());

                startActivity(i);
            }
        });
    }
    //Mensaje de error, Vibra y muestra un mensaje
    public void err_login()
    {
        Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        Toast toast1 = Toast.makeText(getApplicationContext(),"Error: debe completar los campos", Toast.LENGTH_SHORT);
        toast1.show();
    }
    /*realiza consulta a servidor y almacena en variables los datos traidos*/
    public boolean loginstatus(String mensaje)
    {
        /*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
        postparameters2send.add(new BasicNameValuePair("opcion","ingresar_mensaje"));
        postparameters2send.add(new BasicNameValuePair("user_id_from",enviar_mensaje.getUser_id_from()));
        postparameters2send.add(new BasicNameValuePair("user_id_to",enviar_mensaje.getUser_id_to()));
        postparameters2send.add(new BasicNameValuePair("mensaje",mensaje));
        postparameters2send.add(new BasicNameValuePair("token",token));


        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameters2send, URL_connect);
        Log.e("envio de datos", "cadena de envio:" + URL_connect + postparameters2send);
        Log.e("envio de datos", "cadena de regeso:" + jdata );

         //si lo que obtuvimos no es null
        if (jdata!=null && jdata.length() > 0)
        {
            JSONObject json_data = new JSONObject(); //creamos un objeto JSON

            try
            {
                if(jdata.getJSONObject(0).getString("logstatus").equals("error_sesion"))
                {
                    Intent i=new Intent(EnviarMensajeProfesorApoderado.this, MainActivity.class);
                    startActivity(i);
                    System.exit(0);
                }else{
                    json_data = jdata.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
                    enviar_mensaje.logstatus=json_data.getInt("logstatus");//almacenamos el valor
                    Log.e("loginstatus","logstatus= "+enviar_mensaje.getLogstatus());//muestro por log que obtuvimos

                    json_data = jdata.getJSONObject(1); //leemos el segundo segmento del json
                    enviar_mensaje.resultado=json_data.getString("resultado");//almacenamos el valor
                    Log.e("loginstatus","resultado= "+enviar_mensaje.getResultado());
                }
            }
            catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e("error","no reconoce try= "+enviar_mensaje.getLogstatus());
            }
            if (enviar_mensaje.getLogstatus()==0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }else
        {	//json obtenido invalido verificar parte WEB.
            Log.e("JSON  ", "ERROR en json, verificar en parte web");
            return false;
        }

    }
    //validamos si no hay ningun campo en blanco
    public boolean checklogindata(String mensaje )
    {
        if 	(mensaje.equals("") )
        {
            Log.e("Login ui", "Error: Debe escribir un mensaje antes de enviar ");
            return false;
        }else
        {
            return true;
        }
    }

    /*		CLASE ASYNCTASK
     *
     * usaremos esta para poder mostrar el dialogo de progreso mientras enviamos y obtenemos los datos
     * podria hacerse lo mismo sin usar esto pero si el tiempo de respuesta es demasiado lo que podria ocurrir
     * si la conexion es lenta o el servidor tarda en responder la aplicacion sera inestable.
     * ademas observariamos el mensaje de que la app no responde.
     */
    class asynclogin extends AsyncTask< String, String, String >
    {
        protected void onPreExecute()
        {
            //para el progress dialog
            pDialog = new ProgressDialog(EnviarMensajeProfesorApoderado.this);
            pDialog.setMessage("Enviando mensaje....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.setProgress(0);

            pDialog.show();
        }
        protected String doInBackground(String... params)
        {
            //enviamos y recibimos y analizamos los datos en segundo plano.
            if (loginstatus(enviar_mensaje.getMensaje())==true)
            {
                return "ok"; //login valido

            }else
            {
                return "err"; //login invalido
            }

        }
        /*Una vez terminado doInBackground segun lo que halla ocurrido
        pasamos a la sig. activity
        o mostramos error*/
        protected void onPostExecute(String result)
        {
            pDialog.dismiss();//ocultamos progess dialog.
            Log.e("onPostExecute=",""+result);
            if (result.equals("ok"))
            {
                Toast toast1 = Toast.makeText(getApplicationContext(),"El mensaje fue enviado con ??xito ", Toast.LENGTH_LONG);
                toast1.show();
                mensaje_a_enviar.setText("");

            }else
            {
                err_login();
            }

        }
    }
}
