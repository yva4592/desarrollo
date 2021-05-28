package com.rbsystem.RubyMoodle;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import AccesoDatos.Conectar;
import entidad.Usuario;
import library.Httppostaux;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity
{
    EditText user;
    EditText pass;
    Button blogin;
    Httppostaux post;
    private ProgressDialog pDialog;

    //llamada a variables clase conectar
    Conectar con = new Conectar();
    String URL_connect= con.URL_connect;
    Usuario usuario = new Usuario();



    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        post=new Httppostaux();

        user= (EditText) findViewById(R.id.edusuario);
        pass= (EditText) findViewById(R.id.edpassword);
        blogin= (Button) findViewById(R.id.Blogin);

        //accion al momento de presionar el boton login
        blogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                //Extraccion de  datos de los EditText
                usuario.rut=user.getText().toString();
                usuario.pass=pass.getText().toString();
                //se comprueba  si estan en blanco
                //si pasamos esa validacion ejecutamos el asynctask pasando el usuario y clave como parametros
                if( checklogindata( usuario.getRut() , usuario.getPass() )==true)
                {
                    new asynclogin().execute(usuario.getRut(),usuario.getPass());

                }
                //si detecto un error en la primera validacion vibrar y mostrar un Toast con un mensaje de error.
                else
                {
                    err_login();
                }
            }
        });
    }
    //Mensaje de error, Vibra y muestra un mensaje
    public void err_login()
    {
        Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        Toast toast1 = Toast.makeText(getApplicationContext(),"Error:Nombre de usuario o password incorrectos", Toast.LENGTH_SHORT);
        toast1.show();
    }
    public void err_login_apoderado()
    {
        Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        Toast toast1 = Toast.makeText(getApplicationContext(),"El apoderado no tiene alumnos registrados", Toast.LENGTH_LONG);
        toast1.show();
    }
    /*realiza consulta a servidor y almacena en variables los datos traidos*/
    public boolean loginstatus(String username ,String password )
    {
        /*Creamos un ArrayList del tipo nombre valor para agregar los datos recibidos por los parametros anteriores
    	 * y enviarlo mediante POST a nuestro sistema para relizar la validacion*/
        ArrayList<NameValuePair> postparameters2send= new ArrayList<NameValuePair>();
        postparameters2send.add(new BasicNameValuePair("opcion","login"));
        postparameters2send.add(new BasicNameValuePair("username",username));
        postparameters2send.add(new BasicNameValuePair("password",password));

        //realizamos una peticion y como respuesta obtenes un array JSON
        JSONArray jdata=post.getserverdata(postparameters2send, URL_connect);
        Log.e("envio de datos", "cadena de envio:" + URL_connect + postparameters2send);
        Log.e("envio de datos", "cadena de regeso:" + jdata );


        /*como estamos trabajando de manera local el ida y vuelta sera casi inmediato
         * para darle un poco realismo decimos que el proceso se pare por unos segundos para poder
         * observar el progressdialog
         * la podemos eliminar si queremos
         */
        //SystemClock.sleep(150);
        //si lo que obtuvimos no es null
        if (jdata!=null && jdata.length() > 0)
        {
            JSONObject json_data = new JSONObject(); //creamos un objeto JSON

            try
            {
                json_data = jdata.getJSONObject(0); //leemos el primer segmento en nuestro caso el unico
                usuario.logstatus=json_data.getInt("logstatus");//almacenamos el valor
                Log.e("loginstatus","logstatus= "+usuario.getLogstatus());//muestro por log que obtuvimos

                json_data = jdata.getJSONObject(1); //leemos el segundo segmento del json
                usuario.rut=json_data.getString("RUT");//almacenamos el valor
                Log.e("loginstatus","Rut= "+usuario.getRut());

                json_data = jdata.getJSONObject(1); //leemos el segundo segmento del json
                usuario.user_id=json_data.getString("USER_ID");//almacenamos el valor
                Log.e("loginstatus","user_id= "+usuario.getUser_id());


                json_data = jdata.getJSONObject(1); //leemos el segundo segmento del json
                usuario.nombre=json_data.getString("NOMBRE");//almacenamos el valor
                Log.e("loginstatus","Nombre= "+usuario.getNombre());

                json_data = jdata.getJSONObject(1); //leemos el segundo segmento del json
                usuario.apellido=json_data.getString("APELLIDO");//almacenamos el valor
                Log.e("loginstatus","Apellido= "+usuario.getApellido());

                json_data = jdata.getJSONObject(1); //leemos el segundo segmento del json
                usuario.email=json_data.getString("email");//almacenamos el valor
                Log.e("loginstatus","Email= "+usuario.getEmail());

                json_data = jdata.getJSONObject(2); //leemos el segundo segmento del json
                usuario.token=json_data.getInt("token");//almacenamos el valor
                Log.e("loginstatus","token= "+usuario.getToken());

            }
            catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.e("error","no reconoce try= "+usuario.getLogstatus());
            }
            /*validamos el valor obtenido
             [{"logstatus":"0"}]   ===>>> FALSE
             [{"logstatus":"1"}]   ===>>> TRUE
             */
            if (usuario.getLogstatus()==0)
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
    public boolean checklogindata(String username ,String password )
    {
        if 	(username.equals("") || password.equals(""))
        {
            android.util.Log.e("Login ui", "Error: Falta completar nombre  de usuario o Password ");
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
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Ingresando....");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.setProgress(0);

            pDialog.show();

        }
        protected String doInBackground(String... params)
        {
            //enviamos y recibimos y analizamos los datos en segundo plano.
            if (loginstatus(usuario.getRut(),usuario.getPass())==true)
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
               if(usuario.getLogstatus() == 1){
                   Intent i=new Intent(MainActivity.this, MenuPrincipal.class);
                   i.putExtra("user",usuario.getRut());
                   i.putExtra("nombre",usuario.getNombre());
                   i.putExtra("logstatus",usuario.getLogstatus());
                   i.putExtra("user_id",usuario.getUser_id());
                   i.putExtra("token",usuario.getToken());
                   i.putExtra("email",usuario.getEmail());
                   i.putExtra("rut",usuario.getRut());


                   startActivity(i);
               }
               //existe apoderado, pero no tiene alumno asignado
               else if(usuario.getLogstatus() == 2){
                   err_login_apoderado();
                }
               //existe apoderado, y tiene alumno
                else if(usuario.getLogstatus() == 3){
                    Intent i=new Intent(MainActivity.this, AlumnoApoderado.class);
                    i.putExtra("user",usuario.getRut());
                    i.putExtra("nombre",usuario.getNombre());
                    i.putExtra("logstatus",usuario.getLogstatus());
                    i.putExtra("user_id",usuario.getUser_id());
                    i.putExtra("token",usuario.getToken());
                    i.putExtra("email",usuario.getEmail());
                    i.putExtra("rut",usuario.getRut());
                    startActivity(i);
                }
            }else
            {
                err_login();
            }

        }
    }
}
