package library;

/**
 * Created by josépablo on 01-12-14.
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

import entidad.Entidad_participantes;

public class Adapter_participantes extends BaseAdapter{

    protected Activity activity;
    protected ArrayList<Entidad_participantes> items;

    public Adapter_participantes(Activity activity, ArrayList<Entidad_participantes> items) {
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
            v = inf.inflate(R.layout.item_participantes, null);
        }

        // Creamos un objeto mensajes
        Entidad_participantes ep = items.get(position);
        //Rellenamos la fotografía

        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(ep.getFoto());

        //Rellenamos el nombre del profesor
        TextView nombrealumno = (TextView) v.findViewById(R.id.nombrealumno);
        nombrealumno.setText(ep.getFirstname()+" "+ep.getLastname());
        //Rellenamos el correo
        TextView correoprofesor = (TextView) v.findViewById(R.id.correoalumno);
        correoprofesor.setText(ep.getEmail());
        // Retornamos la vista
        return v;
    }
}