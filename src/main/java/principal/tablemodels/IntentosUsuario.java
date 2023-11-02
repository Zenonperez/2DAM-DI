/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal.tablemodels;

import dto.Intent;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ziku
 */
public class IntentosUsuario extends AbstractTableModel {
    private ArrayList<Intent> intentos;
    private String[] columnas = {"ID", "IDEjercio","NombreEjercico", "Inicio", "Fin", "Video"};
    
    public IntentosUsuario(ArrayList<Intent> intentos){
        this.intentos = intentos;
    }
    
    
    @Override
    public int getRowCount() {
        return intentos.size();
        
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            case 0:
                return intentos.get(rowIndex).getId();
            case 1:
                return intentos.get(rowIndex).getIdEjercicio();
            case 2:
                return intentos.get(rowIndex).getNombreEjercicio();
            case 3:
                return intentos.get(rowIndex).getTimestamp_Inicio();
            case 4:
                return intentos.get(rowIndex).getTimestamp_Fin();
            case 5:
                return intentos.get(rowIndex).getVideofile();
        }
                
        return null;
    }
    
    @Override
    public String getColumnName(int column){
        return columnas[column];
    }
    
}
