/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.tablemodels;

import data.DataAccess;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import dto.Intent;

/**
 *
 * @author Ziku
 */
public class IntentosPendientesTableModel extends AbstractTableModel {

    private ArrayList<Intent> intentos;
    private String[] columnas = {"ID", "IDUser", "NomEjer", "Video"};

    public IntentosPendientesTableModel(ArrayList<Intent> intentos) {
        this.intentos = intentos;
    }

    @Override
    public int getRowCount() {
        return intentos.size();

    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return intentos.get(rowIndex).getId();
            case 1:
                return intentos.get(rowIndex).getIdUsuari();
            case 2:
                return intentos.get(rowIndex).getNombreEjercicio();
            case 3:
                return intentos.get(rowIndex).getVideofile();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
