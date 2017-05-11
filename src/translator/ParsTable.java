/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.util.ArrayList;

/**
 *
 * @author Алексей
 */
public class ParsTable {
    private RowPars[] rows = new RowPars[10000];
    private ArrayList indexes;
    
    public ParsTable(){
        this.indexes = new ArrayList();
        for(int i = 0; i < 10000; i++)
            rows[i] = new RowPars("", "");
    }
    
    public RowPars[] getRowPars(){
        return rows;
    }
    
    public ArrayList getIndexes(){
        return indexes;
    }
    
    public boolean add(String name, String number){
        int index = HashTable.getHash(name, 10000);

        while(!rows[index].getName().equals("")){
            index++;
        }
        if(index >= 10000)
            return false;
        rows[index] = new RowPars(name, number);
        indexes.add(index);
        System.out.println("indexPars: " + index);
        return true;
    }
    
     public int find(String name){
        if( name.equals("") )
            return -1;
        int index = HashTable.getHash(name, 10000);
        if( rows[index].getName().equals("") )
            return -1;
        while(!rows[index].getName().equals(name) && index < 10000)
            index++;
        return index < 10000 ? index : -1;
    }
}
