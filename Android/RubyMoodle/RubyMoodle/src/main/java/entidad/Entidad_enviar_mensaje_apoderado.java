package entidad;

/**
 * Created by josépablo on 07-12-14.
 */
public class Entidad_enviar_mensaje_apoderado
{
    public String user; // traida desde mensajes
    public String user_id_to; //traida desde mensajes
    public String user_id_from;// traida desde mensajes
    public String smallmessage;// traida desde mensajes
    public String fecha_creacion;// traida desde mensajes
    public String mensaje;
    public int logstatus;
    public String resultado;
    public int id_mensaje;

    public Entidad_enviar_mensaje_apoderado(){

    }

    public Entidad_enviar_mensaje_apoderado(String user, String user_id_to, String user_id_from, String smallmessage, String fecha_creacion, String mensaje){
        super();
        this.setUser(user);
        this.setUser_id_to(user_id_to);
        this.setUser_id_from(user_id_from);
        this.setSmallmessage(smallmessage);
        this.setFecha_creacion(fecha_creacion);
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser_id_to() {
        return user_id_to;
    }

    public void setUser_id_to(String user_id_to) {
        this.user_id_to = user_id_to;
    }

    public String getUser_id_from() {
        return user_id_from;
    }

    public void setUser_id_from(String user_id_from) {
        this.user_id_from = user_id_from;
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

    public String getMensaje(){
        return  mensaje;
    }

    public void  setMensaje(String mensaje){
        this.mensaje = mensaje;
    }
    public int getLogstatus() {
        return logstatus;
    }

    public void setLogstatus(int logstatus) {
        this.logstatus = logstatus;
    }

    public String getResultado(){
        return  resultado;
    }

    public void  setResultado(String resultado){
        this.resultado = resultado;
    }

    public int getId_mensaje() {
        return id_mensaje;
    }

    public void setId_mensaje(int id_mensaje) {
        this.id_mensaje = id_mensaje;
    }
}
