package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 09-12-14.
 */
public class Entidad_documentos {
    private Drawable foto;
    public int id_documento;
    public int id_category;
    public String fullname;
    public String id_curso;
    public String name_documento;
    public String disponible_desde;
    private long id;

    public Entidad_documentos(){

    }
    public Entidad_documentos(Drawable foto,int id_documento,int id_category,String fullname,String id_curso,String name_documento,String disponible_desde){
        super();
        this.setFoto(foto);
        this.setId_documento(id_documento);
        this.setId_category(id_category);
        this.setFullname(fullname);
        this.setId_curso(id_curso);
        this.setName_documento(name_documento);
        this.setDisponible_desde(disponible_desde);
    }


    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId_curso() {
        return id_curso;
    }

    public void setId_curso(String id_curso) {
        this.id_curso = id_curso;
    }

    public String getName_documento() {
        return name_documento;
    }

    public void setName_documento(String name_documento) {
        this.name_documento = name_documento;
    }

    public String getDisponible_desde() {
        return disponible_desde;
    }

    public void setDisponible_desde(String disponible_desde) {
        this.disponible_desde = disponible_desde;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
