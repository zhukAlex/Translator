/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Алексей
 */
public class MyTable implements TableModel {
    TableRow[] t;
    ArrayList<TableModelListener> listeners = new ArrayList<TableModelListener>();
    public MyTable(TableRow[] t){
        super();
        this.t = t;
    }
    @Override
    public int getRowCount() {
        return t.length;
    }
    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Операция";
                break;
            case 1:
                result = "Приоритет";
                break;
            case 2:
                result = "Ассоциативность";
                break;
            case 3:
                result = "Машинная команда";
                break;
            }
        return result;
    }
    
    @Override
    public Object getValueAt(int r, int c) {
        switch (c) {
            case 0:
                return t[r].name;
            case 1:
                return t[r].prioritie;
            case 2:
                return t[r].associativitie;
            case 3:
                return t[r].machineCommand;
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
