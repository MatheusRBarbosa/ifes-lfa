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

    public static void main(String[] args) throws IOException {
        String[] keys = {"symbols-in", "symbols-out", "states", "start", "finals", "trans", "out-fn"};
        String fileIn = "<<error>>";
        String fileOut = "<<error>>";

        if (args[0].equals("-i")) {
            fileIn = args[1];
        } else if (args[0].equals("-o")) {
            fileOut = args[1];
        }

        if (args[2].equals("-o")) {
            fileOut = args[3];
        } else if (args[2].equals("-i")) {
            fileIn = args[3];
        }

        if(fileIn.equals("<<error>>") || fileOut.equals("<<error>>")){
            System.out.println("Erro ao encontrar e/ou abrir arquivo");
        }
        else{
            
            rFile file = new rFile(fileIn, fileOut, keys);
        
            Automato automato = new Automato(file.readFile());
            System.out.println("== Arquivo com sucesso ! ==");
            automato.convert();

            file.writeFile(automato.getAutomato());
        }
        
    }

}
