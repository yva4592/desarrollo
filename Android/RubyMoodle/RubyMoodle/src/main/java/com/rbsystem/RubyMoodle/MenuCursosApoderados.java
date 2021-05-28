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
public class MenuCursosApoderados extends ActionBarActivity
{
    String user;
    String curso;
    String user_id;
    String token;
    String logstatus;
    TextView txt_curso;
    ImageButton miProfesor;
    ImageButton misDocumentos;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cursos_apoderados);

        txt_curso= (TextView) findViewById(R.id.usr_curso);
        Bundle extras = getIntent().getExtras();
        //Obtenemos datos enviados en el intent.
       if (extras != null)
        {
            user  = extras.getString("user");//usuario
            curso  = extras.getString("curso");//password
            user_id = extras.getString("user_id");
            token = extras.getString("token");
            logstatus = extras.getString("logstatus");
            Log.e("Clase MenuCursosApoderados", "curso recibido: " + curso);
            Log.e("Clase MenuCursosApoderados","usuario recibido: "+user);
            Log.e("CLase MenuCursosApoderados","user_id recibido: "+user_id);
            Log.e("CLase MenuCursosApoderados","token recibido: "+token);
            Log.e("CLase MenuCursosApoderados","logstatus recibido: "+logstatus);
        }
        else
        {
            user="error";
            curso="error";
            user_id ="error";
            token ="error";
            logstatus ="error";
        }
        //cambiamos texto al nombre del curso
        txt_curso.setText(curso);

        miProfesor = (ImageButton) findViewById(R.id.miProfesor);
        miProfesor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0)
            {
                //lo que queremos que pase cuando se pulse

                Intent i=new Intent(MenuCursosApoderados.this, Profesor.class);
                i.putExtra("user",user);
                i.putExtra("curso",curso);
                i.putExtra("user_id",user_id);
                i.putExtra("token",token);
                i.putExtra("logstatus",logstatus);

                startActivity(i);

            }
        });
        misDocumentos = (ImageButton) findViewById(R.id.misDocumentos);
        misDocumentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0)
            {
                //lo que queremos que pase cuando se pulse
                Intent i=new Intent(MenuCursosApoderados.this, Documentos.class);
                i.putExtra("user",user);
                i.putExtra("user_id",user_id);
                i.putExtra("curso",curso);
                i.putExtra("token",token);

                startActivity(i);
            }
        });
    }
}
