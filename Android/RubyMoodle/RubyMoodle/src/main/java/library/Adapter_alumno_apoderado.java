package library;

/**
 * Created by josépablo on 28-11-14.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rbsystem.RubyMoodle.R;

import java.util.ArrayList;

import entidad.Entidad_alumno_apoderado;
import entidad.Entidad_eventos;

public class Adapter_alumno_apoderado extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Entidad_alumno_apoderado> items;

    public Adapter_alumno_apoderado(Activity activity, ArrayList<Entidad_alumno_apoderado> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Generamos una convertView por motivos de eficiencia
        View v = convertView;

        //Asociamos el layout de la lista que hemos creado
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_alumno_apoderado, null);
        }

        // Creamos un objeto directivo
        Entidad_alumno_apoderado eaa = items.get(position);


         //Rellenamos la fotografÃ­a
        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(eaa.getFoto());
        //Rellenamos el rut
        TextView rut = (TextView) v.findViewById(R.id.rut);
        rut.setText(eaa.getRut());
        //Rellenamos el nombre
        TextView nombre = (TextView) v.findViewById(R.id.nombre);
        nombre.setText(eaa.getNombre()+" " +eaa.getApellido());

        TextView email = (TextView) v.findViewById(R.id.email);
        email.setText(eaa.getEmail());

        // Retornamos la vista
        return v;
    }
}
