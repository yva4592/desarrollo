package library;

/**
 * Created by josépablo on 01-12-14.
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

import entidad.Entidad_mensajes;

public class Adapter_mensajes extends BaseAdapter{

    protected Activity activity;
    protected ArrayList<Entidad_mensajes> items;

    public Adapter_mensajes(Activity activity, ArrayList<Entidad_mensajes> items) {
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
            v = inf.inflate(R.layout.item_mensajes, null);
        }

        // Creamos un objeto mensajes
        Entidad_mensajes em = items.get(position);
        //Rellenamos la fotografía

        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(em.getFoto());


        //Rellenamos el subject
        TextView subject = (TextView) v.findViewById(R.id.subject);
        subject.setText(em.getSubject());


        // Retornamos la vista
        return v;
    }
}