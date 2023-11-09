/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.tablemodels;

import dto.Usuari;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ziku
 */
public class UsuariosTableModel extends AbstractTableModel {

    private ArrayList<Usuari> usuarios;
    private String[] columnas = {"ID", "Nombre"};

    public UsuariosTableModel(ArrayList<Usuari> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();

    }

    @Override
    public int getColumnCount() {
        return 2;
    }

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

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
