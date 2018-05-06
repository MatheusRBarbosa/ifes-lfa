/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lfa;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathe
 */
public class Automato {
    
    private List<ArrayList<String>> automato;
    
    
    public Automato (List<ArrayList<String>> array){
        this.automato = array;
    }
    
    public List<ArrayList<String>> getAutomato(){
        return this.automato;
    }
    
    public List<ArrayList<String>> convert ( List<ArrayList<String>> automatoList){
        if(automatoList.get(0).equals("moore")){
            return toMealy(automatoList);
        }
        else if(automatoList.get(0).equals("mealy")){
            return toMoore(automatoList);
        }
        return null;
    }

    private List<ArrayList<String>> toMealy(List<ArrayList<String>> automatoList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private List<ArrayList<String>> toMoore(List<ArrayList<String>> automatoList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
