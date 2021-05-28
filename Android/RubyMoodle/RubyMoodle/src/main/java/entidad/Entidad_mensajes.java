package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 01-12-14.
 */
public class Entidad_mensajes {
    public Drawable foto;
    public String useridfrom;
    public String useridto;
    public String subject;
    public String fullmessage;
    public String smallmessage;
    public String fecha_creacion;
    public int id_mensaje;
    public long id;

    public Entidad_mensajes(){

    }
    public Entidad_mensajes(Drawable foto,String useridfrom,String useridto,String subject,String fullmessage,String smallmessage,String fecha_creacion, long id){
        this.setFoto(foto);
        this.setUseridfrom(useridfrom);
        this.setUseridto(useridto);
        this.setSubject(subject);
        this.setFullmessage(fullmessage);
        this.setSmallmessage(smallmessage);
        this.setFecha_creacion(fecha_creacion);
        this.setId(id);
    }
    public Entidad_mensajes(Drawable foto,String useridfrom,String useridto,String subject,String fullmessage,String smallmessage,String fecha_creacion,int id_mensaje){
        this.setFoto(foto);
        this.setUseridfrom(useridfrom);
        this.setUseridto(useridto);
        this.setSubject(subject);
        this.setFullmessage(fullmessage);
        this.setSmallmessage(smallmessage);
        this.setFecha_creacion(fecha_creacion);
        this.setId_mensaje(id_mensaje);

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }
}
