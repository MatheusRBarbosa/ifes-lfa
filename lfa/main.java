/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lfa;

import java.io.IOException;

/**
 *
 * @author mathe
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String[] keys = {"symbols-in", "symbols-out", "states", "start", "finals", "trans", "out-fn"};
       
        //String fileIn = "C:/Users/mathe/Documents/NetBeansProjects/TrabLFA/src/arquivosTeste/moore1.lisp";
        //String fileOut = "C:/Users/mathe/Documents/NetBeansProjects/TrabLFA/src/arquivosTeste/teste.lisp";
        String fileIn = args[0];
        String fileOut = args[1];
        
        rFile file = new rFile(fileIn, fileOut, keys);
        
        Automato automato = new Automato(file.readFile());
        //automato.imprime();
        System.out.println("==CONVERSAO==");
        automato.convert();
        //automato.imprime();
        
        file.writeFile(automato.getAutomato());
    }
    
}
