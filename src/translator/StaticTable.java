/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Алексей
 */
public class StaticTable implements Serializable {
    TableRow[] rows = new TableRow[1000];
    ArrayList indexes;
    
    StaticTable(){
        this.indexes = new ArrayList();
        for(int i = 0; i < 1000; i++)
            rows[i] = new TableRow("", "", "", "");
    }
    
    public boolean add(String name, String associativitie, String prioritie, String machineCommand){
        int index = HashTable.getHash(name, 1000);

        while( !rows[index].name.equals("") ) {
          /*  if( rows[index].name.equals("WAS_DELETED") )
                break;*/
            if( rows[index].name.equals(name) )
                return false;
            index++;
        }
        
        rows[index] = new TableRow(name, prioritie, associativitie, machineCommand);
        indexes.add(index);
        System.out.println("index: " + index);
        return true;
    }
    
    public boolean change(String name, String assoc, String prior, String machine){
        int index = find(name);
        if(index < 0)
            return false;
        rows[index] = new TableRow(name, prior, assoc, machine);
        return true;
    }
    
    public int find(String name){
        if( name.equals("") )
            return -1;
        int index = HashTable.getHash(name, 1000);
        if( rows[index].name.equals("") )
            return -1;
        while(!rows[index].name.equals(name) && index < 1000)
            index++;
        return index < 1000 ? index : -1;
    }
    
    public boolean delete(String name){
        int index = find(name);
        if(index < 0)
            return false;
        indexes.remove((Object)index);
        rows[index] = new TableRow("", "", "", "");
        return true;
    }
}
