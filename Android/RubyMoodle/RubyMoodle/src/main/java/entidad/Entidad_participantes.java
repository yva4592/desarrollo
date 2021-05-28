package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 01-12-14.
 */
public class Entidad_participantes {
    public Drawable foto;
    public String id_participante;
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    public long id;

    public Entidad_participantes(){

    }
    public Entidad_participantes(Drawable foto, String id_participante, String username, String firstname, String lastname, String email){
        this.setFoto(foto);
        this.setId_participante(id_participante);
        this.setUsername(username);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setEmail(email);
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }


    public String getId_participante() {
        return id_participante;
    }

    public void setId_participante(String id_participante) {
        this.id_participante = id_participante;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
