/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 * Review es el modelo que se utilizara para crear los objetos Review, los cuales son las valoraciones que se le da alos intentos realizados por los usuarios.
 * @author Zenon Perez
 */
public class Review {
    
    //Declaramos las varaibles del objeto.
    /**
     * Es la id de la review.
     */
    private int id;
    /**
     * Es la id del intento de la review.
     */
    private int idIntent;
    /**
     * Es la id de la persona que ha evaluado el intento.
     */
    private int idReviewer;
    /**
     * Es la nota de valoracion que se le ha dado en la review de un intento.
     */
    private int valoracion;
    /**
     * Es el comentario que se ha hecho en la review de un intento.
     */
    private String comentario;

    /**
     * Getter de la id de una review
     * @return devuelve la id de una review
     */
    public int getId() {
        return id;
    }
    /**
     * Setter de la id de una review
     * @param id es la id la cual substituira a la antigua
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Getter de la id del intento de una review
     * @return devolvera la id del intento de una review
     */
    public int getIdIntent() {
        return idIntent;
    }
    /**
     * Setter de la id del intento de una review
     * @param idIntent id que substituira a la id antigua de un intento
     */
    public void setIdIntent(int idIntent) {
        this.idIntent = idIntent;
    }

    /**
     * Getter de la id de la persona que ha hecho la review
     * @return nos devuelve el id del reviewer
     */
    public int getIdReviewer() {
        return idReviewer;
    }
    /**
     * Setter de la id de la persona que ha hecho la review
     * @param idReviewer id que substituira a la id antigua de una review
     */
    public void setIdReviewer(int idReviewer) {
        this.idReviewer = idReviewer;
    }
    /**
     * Getter de la valoracion de una review
     * @return devuelve la valoracion de una review
     */
    public int getValoracion() {
        return valoracion;
    }
    /**
     * Setter de la valroacion de una review
     * @param valoracion sera la valoracion que substituira a la antigua
     */
    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
    /**
     * Getter del comentario de una review 
     * @return nos devuelve el comentario de una review.
     */
    public String getComentario() {
        return comentario;
    }
    /**
     * Setter del comentario de una review
     * @param comentario sera el comentario que substituira al antiguo
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
