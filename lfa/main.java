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
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        rFile file = new rFile("C:/Users/mathe/Documents/NetBeansProjects/TrabLFA/src/arquivosTeste/moore.lisp");
        Automato automato = new Automato(file.readFile());
        List<ArrayList<String>> automatoList = automato.getAutomato();
        
        System.out.println(automatoList.get(0));
       /* for(int i=0;i<automatoList.size();i++){
            System.out.println(automatoList.get(i));
        }*/
    }
    
}
