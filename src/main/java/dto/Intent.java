package dto;

/**
 * Intent es el modelo que se utilizara para crear los objetos intentos, los cuales son unos intentos de ejercicios han realizado los usuarios.
 * @author Zenon Perez
 */
public class Intent {
//Declaramos las variables del objeto.
    /**
     * Es el id del intento.
     */
    private int id;
    /**
     * Es id del usuario que pertenece al intento.
     */
    private int idUsuari;
    /**
     * Es el id de un ejericio que pertenece al intento.
     */
    private int idEjercicio;
    /**
     * El el nombre del usuario del intento.
     */
    private String NombreUsuario;
    /**
     * Es el nombre del ejercicio del intento.
     */
    private String NombreEjercicio;
    /**
     * Es en la fecha y hora que empieza el intento.
     */
    private String timestamp_Inicio;
    /**
     * Es la fecha y hora que acaba el intento.
     */
    private String timestamp_Fin;
    /**
     * Es el nombre que tiene el archivo de video del intento.
     */
    private String videofile;

    /**
     * Getter de la id de un intento.
     * @return devuelve la id del intento
     */
    public int getId() {
        return id;
    }
    /**
     * Setter de la id de un intento.
     * @param id la id que substituira a la antigua
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter de la id del usuario del intento
     * @return devuelve la id del usuario.
     */
    public int getIdUsuari() {
        return idUsuari;
    }
    /**
     * Setter de la id del usuario del intento
     * @param idUsuari la id del usuario que substituira a la antigua
     */
    public void setIdUsuari(int idUsuari) {
        this.idUsuari = idUsuari;
    }
    /**
     * Getter de la id de un ejercicio del intento.
     * @return devuelve la id del ejercicio.
     */
    public int getIdEjercicio() {
        return idEjercicio;
    }
    /**
     * Setter de la id de un ejercicio del intento.
     * @param idEjercicio la id del ejercicio que substituira a la antigua.
     */
    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }
    /**
     * Getter del timeStamp_incio del intento.
     * @return devuelve el timpeStamp_inicio del intento.
     */
    public String getTimestamp_Inicio() {
        return timestamp_Inicio;
    }
    /**
     * Setter del timeStamp_incio del intento
     * @param timestamp_Inicio el timestamp incio que substituira al antiguo
     */
    public void setTimestamp_Inicio(String timestamp_Inicio) {
        this.timestamp_Inicio = timestamp_Inicio;
    }

    /**
     * Getter del timeStamp fin de un intento
     * @return devolvera el timeStamp fin de un ejercicio
     */
    public String getTimestamp_Fin() {
        return timestamp_Fin;
    }
    /**
     * Setter del timeStamp fin de un intento
     * @param timestamp_Fin el timeStamp fin que substituira al antiguo
     */
    public void setTimestamp_Fin(String timestamp_Fin) {
        this.timestamp_Fin = timestamp_Fin;
    }
    /**
     * Getter del videoFile de un intento
     * @return devolvera el videoFile del intento
     */
    public String getVideofile() {
        return videofile;
    }
    /**
     * Setter del videofile de un intento.
     * @param videofile parametro el cual substituira al videofile antiguo del intento. 
     */
    public void setVideofile(String videofile) {
        this.videofile = videofile;
    }
    /**
     * Getter del nombre de un usuario del intento.
     * @return devulve el nombre de un usuario del intento.
     */
    public String getNombreUsuario() {
        return NombreUsuario;
    }
    /**
     * Setter del nombre de un usuario del intento
     * @param NombreUsuario cambia el nombre del usuario del intento por el parametro que hemos puesto.
     */
    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }
    /**
     * ´Getter del nombre de un ejercicio del intento
     * @return devuelve el nombre del ejercicio del intento
     */
    public String getNombreEjercicio() {
        return NombreEjercicio;
    }
    /**
     * Setter del nombre de un ejercicio del intento
     * @param NombreEjercicio devuelve el nombre del ejercicio del intento.
     */
    public void setNombreEjercicio(String NombreEjercicio) {
        this.NombreEjercicio = NombreEjercicio;
    }

}
