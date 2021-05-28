package com.rbsystem.RubyMoodle;

/**
 * Created by josépablo on 27-09-14.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;




/*PANTALLA DE MENU PRINCIPAl*/
public class MenuPrincipal extends ActionBarActivity {
    String user;
    String nombre;
    String logstatus;
    String user_id;
    String email;
    String rut;

    int token;
    TextView txt_nombre;
    TextView txt_rut;
    TextView txt_email;

    ImageButton miscursos;
    ImageButton miAsistencia;
    ImageButton misEventos;
    ImageButton misnotas;




    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);

        txt_nombre= (TextView) findViewById(R.id.usr_nombre);
        txt_email= (TextView) findViewById(R.id.txt_email);
        txt_rut= (TextView) findViewById(R.id.txt_rut);

        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
       if (extras != null)
        {

            user  = extras.getString("user");//usuario
            nombre  = extras.getString("nombre");//password
            logstatus =  Integer.toString(extras.getInt("logstatus"));
            user_id = extras.getString("user_id");
            token = extras.getInt("token");
            rut = extras.getString("rut");
            email= extras.getString("email");

            Log.e("Clase MenuPrincipal", "user recibido: " + user);
            Log.e("Clase MenuPrincipal","nombre recibido: "+nombre);
            Log.e("CLase MenuPrincipal","logstatus recibido: "+logstatus);
            Log.e("CLase MenuPrincipal","user_id recibido: "+user_id);
            Log.e("CLase MenuPrincipal","token recibido: "+token);
        }
        else
        {
            logstatus = "error";
            user="error";
            nombre="error";
            user_id= "error";
            token=0;
            rut = "error";
            email = "error";
        }
        //cambiamos texto al nombre del usuario logueado
        //Usuario usuario = new Usuario();
        //String u = usuario.getRol();
        txt_nombre.setText(nombre);
        txt_rut.setText(rut);
        txt_email.setText(email);




        miscursos = (ImageButton) findViewById(R.id.misAsignaturas);
        miscursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0)
            {
                //lo que queremos que pase cuando se pulse
                Intent i=new Intent(MenuPrincipal.this, Cursos.class);
                i.putExtra("user",user);
                i.putExtra("user_id",user_id);
                i.putExtra("token",token);
                i.putExtra("logstatus",logstatus);

               startActivity(i);
            }
        });
        miAsistencia = (ImageButton) findViewById(R.id.miAsistencia);
        miAsistencia.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0)
            {
                //lo que queremos que pase cuando se pulse
                Intent i=new Intent(MenuPrincipal.this, Asistencia.class);
                i.putExtra("user",user);
                i.putExtra("token",token);

                startActivity(i);

            }
        });
        misEventos = (ImageButton) findViewById(R.id.misEventos);
        misEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0)
            {
                //lo que queremos que pase cuando se pulse
                Intent i=new Intent(MenuPrincipal.this, Eventos.class);
                i.putExtra("user",user);
                i.putExtra("token",token);

                startActivity(i);
            }
        });
        misnotas = (ImageButton) findViewById(R.id.misCalificaciones);
        misnotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0)
            {
                //lo que queremos que pase cuando se pulse
                Intent i=new Intent(MenuPrincipal.this, Notas.class);
                i.putExtra("user",user);
                i.putExtra("token",token);

                startActivity(i);
            }
        });
    }

}
