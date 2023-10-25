
package dto;

import java.util.Date;

/**
 *
 * @author Ziku
 */
public class Intent {
    
    private int id;
    private int idUsuari;
    private int idEjercicio;
    private String NombreUsuario;
    private String NombreEjercicio;
    private String timestamp_Inicio;
    private String timestamp_Fin;
    private String videofile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuari() {
        return idUsuari;
    }

    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public String getTimestamp_Inicio() {
        return timestamp_Inicio;
    }

    public void setTimestamp_Inicio(String timestamp_Inicio) {
        this.timestamp_Inicio = timestamp_Inicio;
    }

    public String getTimestamp_Fin() {
        return timestamp_Fin;
    }

    public void setTimestamp_Fin(String timestamp_Fin) {
        this.timestamp_Fin = timestamp_Fin;
    }

    public String getVideofile() {
        return videofile;
    }

    public void setVideofile(String videofile) {
        this.videofile = videofile;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getNombreEjercicio() {
        return NombreEjercicio;
    }

    public void setNombreEjercicio(String NombreEjercicio) {
        this.NombreEjercicio = NombreEjercicio;
    }
    
    
    
    
}
