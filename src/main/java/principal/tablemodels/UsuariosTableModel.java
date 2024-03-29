/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.tablemodels;

import dto.Usuari;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * UsuariosTableModel es una clase qie hace de modelo a la tabla de Usuarios
 * @author Zenon Perez
 */
public class UsuariosTableModel extends AbstractTableModel {
    //Declaramos las variables globales de la clase.
    private ArrayList<Usuari> usuarios;
    private String[] columnas = {"ID", "Nombre"};
    /**
     * Metodo constructor de UsuariosTableModel que crea el objeto que sera la tabla de usuarios
     * @param usuarios es una lista de usuarios que esta dentro de la tabla. Esos usuarios son parte de la tabla.
     */
    public UsuariosTableModel(ArrayList<Usuari> usuarios) {
        this.usuarios = usuarios;
    }
    /**
     * Metodo getter que devuelve la cantidad de filas que hay en la tabla
     * @return devuelve int con el valor del numero de filas de la tabla.
     */
    @Override
    public int getRowCount() {
        return usuarios.size();

    }
    /**
     * Getter que cuenta las columnas que hay en la tabla.
     * @return devuelve el valor 2 debido a ques solo hay dos columnas en la tabla.
     */
    @Override
    public int getColumnCount() {
        return 2;
    }
    /**
     * Metodo que se encarga de coger un valor de la un indice de fila y columna correspondiente
     * @param rowIndex determina el indice de fila que buscara el valor
     * @param columnIndex determina el inidice de columna para buscar un valor
     * @return devolvera el valor que coincida con el indice de la columna y de la fila dadas en el parametro.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return usuarios.get(rowIndex).getId();
            case 1:
                return usuarios.get(rowIndex).getNombre();
        }

        return null;
    }
    /**
     * Metodo getter que devuelve el nombre de la columna
     * @param column es el inidice de la columna la cual queremos devolver el nombre.
     * @return devuelve el nombre dado por el indice de la columna.
     */
    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
