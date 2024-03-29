/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 * Usuari es el modelo que se utilizara para crear los objeto tipo Usuari, que representan a los usarios de la base de datos que realizan los intentos de los ejercicios.
 * @author Zenon Perez
 */
public class Usuari {
    //Declaramos la variables globales del objeto.
    /**
     * Es la id del Usuario.
     */
    private int id;
    /**
     * Es el nombre del usuario.
     */
    private String nombre;
    /**
     * Es el mail del usuario.
     */
    private String email;
    /**
     * Es la contrasena cifrada del usuario.
     */
    private String passwordHash;
    /**
     * Es el archivo de la foto de perfil del usuario.
     */
    private byte[] foto;
    /**
     * Es la varaible que si es true inidica que es instructor y si es false no lo es.
     */
    private boolean instructor;
    
    /**
     * Getter de la id del usario
     * @return devuelve la id del usuario
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de la id del usuario
     * @param id es la id que substituira a la antigua
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Getter del nombre del usuario
     * @return devuelve el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Setter del nombre del usuario
     * @param nombre es el nombre que substituira al antiguo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Getter del mail de un usuario
     * @return devuelve el mail del usuario.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Setter del mail de un usuario
     * @param email es el mail que substituira al antiguo
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Getter la contrasenya cifrada de un usuario
     * @return devuelve la contrasenya cifrada de un usuario 
     */
    public String getPasswordHash() {
        return passwordHash;
    }
    /**
     * Setter de la contrasenya cifrada de un usuario
     * @param passwordHash contrasenya que substituira a la antigua
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    /**
     * Getter de la foto de un usuario
     * @return devuelve la foto de un usuario.
     */
    public byte[] getFoto() {
        return foto;
    }
    /**
     * Setter de la foto de un usuario
     * @param foto foto que substituira a la antigua 
     */
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
    /**
     * Getter booleano de si el usuario es un instructor
     * @return devuelve un valor booleano
     */
    public boolean isInstructor() {
        return instructor;
    }
    /**
     * Setter booleano de si el usuario es un instructor
     * @param instructor cambiara el valor booleano a true o false.
     */
    public void setInstructor(boolean instructor) {
        this.instructor = instructor;
    }

}
