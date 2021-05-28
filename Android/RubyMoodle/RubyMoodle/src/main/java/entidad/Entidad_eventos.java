package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 09-12-14.
 */
public class Entidad_eventos {
    private Drawable foto;
    public String asignatura;
    public String desde;
    public String hasta;
    public String evento;

    private long id;

    public Entidad_eventos(){

    }
    public Entidad_eventos(Drawable foto, String asignatura, String desde, String hasta, String evento){
        super();
        this.setFoto(foto);
        this.setAsignatura(asignatura);
        this.setDesde(desde);
        this.setHasta(hasta);
        this.setEvento(evento);
    }


    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }
}
