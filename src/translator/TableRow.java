/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translator;

import java.io.Serializable;

/**
 *
 * @author Алексей
 */
public class TableRow implements Serializable {
    String name = "";
    String prioritie = "";
    String associativitie = "";
    String machineCommand = "";
    public TableRow(String name, String prioritie, String associativitie, String machineCommand){
        this.associativitie = associativitie;
        this.machineCommand = machineCommand;
        this.name = name;
        this.prioritie = prioritie;
    }
}
