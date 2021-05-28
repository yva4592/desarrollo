package entidad;

/**
 * Created by josépablo on 28-11-14.
 */
import android.graphics.drawable.Drawable;


public class Entidad_asistencia {
    private Drawable foto;
    public String porcentaje_presente;
    public String dias_presentes;
    public String dias_ausentes;
    public String dias_ausentes_justificado;
    private String fecha;
    private String estado;
    private long id;

    public Entidad_asistencia(Drawable foto,String fecha,String estado ) {
        super();
        this.setFoto(foto);
        this.setFecha(fecha);
        this.setEstado(estado);

    }

    public Entidad_asistencia() {

    }


    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public String getPorcentaje_presente() {
        return porcentaje_presente;
    }

    public void setPorcentaje_presente(String porcentaje_presente) {
        this.porcentaje_presente = porcentaje_presente;
    }

    public String getDias_presentes() {
        return dias_presentes;
    }

    public void setDias_presentes(String dias_presentes) {
        this.dias_presentes = dias_presentes;
    }

    public String getDias_ausentes() {
        return dias_ausentes;
    }

    public void setDias_ausentes(String dias_ausentes) {
        this.dias_ausentes = dias_ausentes;
    }

    public String getDias_ausentes_justificado() {
        return dias_ausentes_justificado;
    }

    public void setDias_ausentes_justificado(String dias_ausentes_justificado) {
        this.dias_ausentes_justificado = dias_ausentes_justificado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
