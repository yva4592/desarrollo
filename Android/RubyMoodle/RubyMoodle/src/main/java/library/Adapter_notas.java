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

import entidad.Entidad_notas;

public class Adapter_notas extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<Entidad_notas> items;

    public Adapter_notas(Activity activity, ArrayList<Entidad_notas> items) {
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
            v = inf.inflate(R.layout.item_notas, null);
        }

        // Creamos un objeto directivo
        Entidad_notas en = items.get(position);


         //Rellenamos la fotografia
        ImageView foto = (ImageView) v.findViewById(R.id.foto);
        foto.setImageDrawable(en.getFoto());
        //Rellenamos el curso
        TextView cursonotas = (TextView) v.findViewById(R.id.cursonotas);
        cursonotas.setText(en.getCurso_notas());
        //Rellenamos el item
        TextView item = (TextView) v.findViewById(R.id.item);
        item.setText(en.getItem_nota());
        //Rellenamos la nota
        TextView nota = (TextView) v.findViewById(R.id.nota);
        nota.setText(en.getNota().substring(0,2));


        // Retornamos la vista
        return v;
    }
}
