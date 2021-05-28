package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 14-12-14.
 */
public class Entidad_alumno_apoderado {
    private Drawable foto;
    public String rut;
    public String user_id;
    public String nombre;
    public String apellido;
    public String email;
    private long id;

    public Entidad_alumno_apoderado(){

    }
    public Entidad_alumno_apoderado(Drawable foto, String rut,String user_id,String nombre,String apellido,String email ){
        super();
        this.setFoto(foto);
        this.setRut(rut);
        this.setUser_id(user_id);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setEmail(email);
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
