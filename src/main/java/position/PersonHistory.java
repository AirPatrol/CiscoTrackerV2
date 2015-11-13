/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package position;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author Daan
 */
public class PersonHistory {
    
    private String name;
    private ArrayList<CiscoPoint> movementHistory;
    
    public PersonHistory(String name, ArrayList<CiscoPoint> movementHistory){
        this.name = name;
        this.movementHistory = movementHistory;
    }

    public String getName() {
        return name;
    }

    public ArrayList<CiscoPoint> getMovementHistory() {
        return movementHistory;
    }
}
