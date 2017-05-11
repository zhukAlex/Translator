/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Алексей
 */
public class MyParsModel implements TableModel {
    RowPars[] t;
    ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();
    public MyParsModel(RowPars[] t){
        super();
        this.t = t;
    }
    @Override
    public int getRowCount() {
        return t.length;
    }
    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Операция";
                break;
            case 1:
                result = "Строка";
                break;
            }
        return result;
    }
    
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return t[r].getName();
            case 1:
                return t[r].getNumber();
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        
    }

}
