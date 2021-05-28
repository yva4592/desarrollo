package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 14-12-14.
 */
public class Entidad_cursos {
    private Drawable foto;
    public String asignatura;
    private long id;

    public Entidad_cursos(){

    }
    public  Entidad_cursos( Drawable foto,String asignatura){
        super();
        this.setFoto(foto);
        this.setAsignatura(asignatura);
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
