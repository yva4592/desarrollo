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

import entidad.Entidad_documentos;

public class Adapter_documentos extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Entidad_documentos> items;

    public Adapter_documentos(Activity activity, ArrayList<Entidad_documentos> items) {
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
            v = inf.inflate(R.layout.item_documentos, null);
        }

        // Creamos un objeto directivo
        Entidad_documentos ed = items.get(position);


         //Rellenamos la fotografÃ­a
        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(ed.getFoto());
        //Rellenamos el nombre del documento
        TextView nombredocumento = (TextView) v.findViewById(R.id.nombredocumento);
        nombredocumento.setText(ed.getName_documento());
        //Rellenamos la fecha de disponibilidad
        TextView disponibledesde = (TextView) v.findViewById(R.id.disponibledesde);
        disponibledesde.setText(ed.getDisponible_desde());


        // Retornamos la vista
        return v;
    }
}
