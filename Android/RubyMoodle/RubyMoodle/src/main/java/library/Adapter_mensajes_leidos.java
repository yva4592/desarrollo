package library;

/**
 * Created by josépablo on 01-12-14.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rbsystem.RubyMoodle.R;

import java.util.ArrayList;

import entidad.Entidad_mensajes_leidos;

public class Adapter_mensajes_leidos extends BaseAdapter{

    protected Activity activity;
    protected ArrayList<Entidad_mensajes_leidos> items;

    public Adapter_mensajes_leidos(Activity activity, ArrayList<Entidad_mensajes_leidos> items) {
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
            v = inf.inflate(R.layout.item_mensajes_leidos, null);
        }
        try{
        // Creamos un objeto mensajes
        Entidad_mensajes_leidos em = items.get(position);

        //Rellenamos la fotografía
        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(em.getFoto());


        //Rellenamos el subject
        TextView subject = (TextView) v.findViewById(R.id.subject);
       //se corta la cadena desde el caracter 18 hasta el -1
        subject.setText(em.getSubject().substring(18,em.getSubject().length()-1));


        //Rellenamos el smallmessage
        TextView smallmessage = (TextView) v.findViewById(R.id.smallmessage);
        smallmessage.setText(em.getSmallmessage());


        //Rellenamos la fecha de creación
        TextView leido = (TextView) v.findViewById(R.id.leido);
        leido.setText(em.getLeido());

        // Retornamos la vista
        }
        catch (Exception e)
        {
                Log.e("Clase mensajes_leidos", "error en el adapater",e);

        }
    return v;
    }
}