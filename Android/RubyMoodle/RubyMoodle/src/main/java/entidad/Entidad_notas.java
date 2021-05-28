package entidad;

import android.graphics.drawable.Drawable;

/**
 * Created by josépablo on 09-12-14.
 */
public class Entidad_notas {
    private Drawable foto;
    public int id_notas;
    public String curso_notas;
    public String item_nota;
    public String nota;
    private long id;

    public Entidad_notas(){

    }
    public Entidad_notas(Drawable foto,int id_notas,String curso_notas,String item_nota,String nota){
        super();
        this.setFoto(foto);
        this.setId_notas(id_notas);
        this.setCurso_notas(curso_notas);
        this.setItem_nota(item_nota);
        this.setNota(nota);
    }
    public Entidad_notas(Drawable foto,String curso_notas,String item_nota,String nota){
        super();
        this.setFoto(foto);
        this.setCurso_notas(curso_notas);
        this.setItem_nota(item_nota);
        this.setNota(nota);
    }

    public Drawable getFoto() {
        return foto;
    }

    public void setFoto(Drawable foto) {
        this.foto = foto;
    }

    public int getId_notas() {
        return id_notas;
    }

    public void setId_notas(int id_notas) {
        this.id_notas = id_notas;
    }

    public String getCurso_notas() {
        return curso_notas;
    }

    public void setCurso_notas(String curso_notas) {
        this.curso_notas = curso_notas;
    }

    public String getItem_nota() {
        return item_nota;
    }

    public void setItem_nota(String item_nota) {
        this.item_nota = item_nota;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
