package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 01-12-14.
 */
public class Entidad_mensajes_apoderado {
    public Drawable foto;
    public String useridfrom;
    public String useridto;
    public String subject;
    public String fullmessage;
    public String smallmessage;
    public String fecha_creacion;
    public String leido;
    public long id;

    public Entidad_mensajes_apoderado(){

    }
    public Entidad_mensajes_apoderado(Drawable foto, String useridfrom, String useridto, String subject, String fullmessage, String smallmessage, String fecha_creacion, long id){
        this.setFoto(foto);
        this.setUseridfrom(useridfrom);
        this.setUseridto(useridto);
        this.setSubject(subject);
        this.setFullmessage(fullmessage);
        this.setSmallmessage(smallmessage);
        this.setFecha_creacion(fecha_creacion);
        this.setId(id);
    }
    public Entidad_mensajes_apoderado(Drawable foto, String useridfrom, String useridto, String subject, String fullmessage, String smallmessage, String fecha_creacion, String leido){
        this.setFoto(foto);
        this.setUseridfrom(useridfrom);
        this.setUseridto(useridto);
        this.setSubject(subject);
        this.setFullmessage(fullmessage);
        this.setSmallmessage(smallmessage);
        this.setFecha_creacion(fecha_creacion);
        this.setLeido(leido);

    }

    public Entidad_mensajes_apoderado(Drawable foto, String useridfrom, String useridto, String subject, String fullmessage, String smallmessage, String fecha_creacion) {
       super();
        this.setFoto(foto);
        this.setUseridfrom(useridfrom);
        this.setUseridto(useridto);
        this.setSubject(subject);
        this.setFullmessage(fullmessage);
        this.setSmallmessage(smallmessage);
        this.setFecha_creacion(fecha_creacion);

    }


    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public String getUseridfrom() {
        return useridfrom;
    }

    public void setUseridfrom(String useridfrom) {
        this.useridfrom = useridfrom;
    }

    public String getUseridto() {
        return useridto;
    }

    public void setUseridto(String useridto) {
        this.useridto = useridto;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFullmessage() {
        return fullmessage;
    }

    public void setFullmessage(String fullmessage) {
        this.fullmessage = fullmessage;
    }

    public String getSmallmessage() {
        return smallmessage;
    }

    public void setSmallmessage(String smallmessage) {
        this.smallmessage = smallmessage;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getLeido() {
        return fecha_creacion;
    }

    public void setLeido(String Leido) {
        this.leido = leido;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
