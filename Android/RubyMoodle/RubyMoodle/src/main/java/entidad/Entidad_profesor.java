package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 07-12-14.
 */
public class Entidad_profesor {
    public Drawable foto;
    public String user_id_to;
    public String username;
    public String firstname;
    public String lastname;
    public String email;
    private long id;

    public Entidad_profesor(){

    }



    public Entidad_profesor(Drawable foto, String user_id_to, String username, String firstname, String lastname, String email) {
       super();
        this.setFoto(foto);
        this.setUser_id_to(user_id_to);
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

    public String getUser_id_to() {
        return user_id_to;
    }

    public void setUser_id_to(String user_id_to) {
        this.user_id_to = user_id_to;
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
