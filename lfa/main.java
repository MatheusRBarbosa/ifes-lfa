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
        String[] keys = {"symbols-in", "symbols-out", "states", "start", "finals", "trans", "out-fn"};
        rFile file = new rFile("C:/Users/mathe/Documents/NetBeansProjects/TrabLFA/src/arquivosTeste/mealy.lisp", keys);
        Automato automato = new Automato(file.readFile());
        //automato.imprime();
        automato.convert();
        System.out.println("==CONVERSAO==");
        automato.imprime();
    }
    
}
