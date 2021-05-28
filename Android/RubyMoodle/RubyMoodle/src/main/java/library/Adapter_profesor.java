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

import entidad.Entidad_profesor;

public class Adapter_profesor extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Entidad_profesor> items;

    public Adapter_profesor(Activity activity, ArrayList<Entidad_profesor> items) {
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
            v = inf.inflate(R.layout.item_profesor, null);
        }

        // Creamos un objeto directivo
        Entidad_profesor ep = items.get(position);


         //Rellenamos la fotografÃ­a
        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(ep.getFoto());
        //Rellenamos el nombre
        TextView nombreprofesor = (TextView) v.findViewById(R.id.nombreprofesor);
        nombreprofesor.setText(ep.getFirstname()+" "+ep.getLastname());
        //Rellenamos el correo
        TextView correoprofesor = (TextView) v.findViewById(R.id.correoprofesor);
        correoprofesor.setText(ep.getEmail());

        // Retornamos la vista
        return v;
    }
}
