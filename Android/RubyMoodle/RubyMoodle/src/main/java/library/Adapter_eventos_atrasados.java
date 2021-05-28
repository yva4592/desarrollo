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

import entidad.Entidad_eventos_atrasados;

public class Adapter_eventos_atrasados extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Entidad_eventos_atrasados> items;

    public Adapter_eventos_atrasados(Activity activity, ArrayList<Entidad_eventos_atrasados> items) {
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
            v = inf.inflate(R.layout.item_eventos_atrasados, null);
        }

        // Creamos un objeto directivo
        Entidad_eventos_atrasados eea = items.get(position);


         //Rellenamos la fotografÃ­a
        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(eea.getFoto());
        //Rellenamos el curso
        TextView asignaturaeventos = (TextView) v.findViewById(R.id.asignatura);
        asignaturaeventos.setText(eea.getAsignatura());
        //Rellenamos la fecha desde
        TextView desde = (TextView) v.findViewById(R.id.desde);
        desde.setText(eea.getDesde());
        //Rellenamos la fecha hasta
        TextView hasta = (TextView) v.findViewById(R.id.hasta);
        hasta.setText(eea.getHasta());
        //Rellenamos la actividad
        TextView evento = (TextView) v.findViewById(R.id.evento);
        evento.setText(eea.getEvento());


        // Retornamos la vista
        return v;
    }
}
