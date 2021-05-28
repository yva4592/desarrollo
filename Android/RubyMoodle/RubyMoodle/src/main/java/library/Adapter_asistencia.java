package library;

/**
 * Created by josépablo on 28-11-14.
 */
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rbsystem.RubyMoodle.R;

import entidad.Entidad_asistencia;

public class Adapter_asistencia extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Entidad_asistencia> items;

    public Adapter_asistencia(Activity activity, ArrayList<Entidad_asistencia> items) {
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
            v = inf.inflate(R.layout.item_asistencia, null);
        }

        // Creamos un objeto directivo
        Entidad_asistencia eas = items.get(position);


         //Rellenamos la fotografÃ­a
        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(eas.getFoto());
        //Rellenamos la fecha
        TextView fecha = (TextView) v.findViewById(R.id.fecha);
        fecha.setText(eas.getFecha());
        //Rellenamos el estado
        TextView estado = (TextView) v.findViewById(R.id.estado);
        estado.setText(eas.getEstado());

        // Retornamos la vista
        return v;
    }
}
