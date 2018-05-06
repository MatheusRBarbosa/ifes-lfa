/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lfa;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author mathe
 */
public class rFile {

    private String fileDir;

    private String keys[] = {"symbols-in", "symbols-out", "states", "start", "finals", "trans"};
    private boolean trans = false;
    private List<ArrayList<String>> automato = new ArrayList<>();

    public rFile(String fileDir) {
        this.fileDir = fileDir;
    }

    public List<ArrayList<String>> readFile() {
        try {
            FileReader file = new FileReader(this.fileDir);
            BufferedReader readF = new BufferedReader(file);

            String line = readF.readLine();

            while (line != null) {
                //System.out.println("-->" + line);
                insertAutomato(line);
                line = readF.readLine();
            }
            file.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nâo encontrado");
        } catch (IOException ex) {
            System.out.println("Erro na abertura do arquivo");
        }
        return this.automato;
    }

    private void insertAutomato(String line) {
        int cont = 0;
        
        StringTokenizer st = new StringTokenizer(line, "() ", true);
        ArrayList<String> auxList = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (!token.equals(" ")) {
                if (!token.equals("(")) {
                    if (!token.equals(")")) {
                        if(token.equals("trans")){
                            this.trans = true;
                        }
                        if (!keyWord(token)) {
                            auxList.add(token);
                            cont++;
                            if(trans && cont == 4){
                                automato.add(auxList);
                                auxList = new ArrayList<>(); 
                                cont = 0;
                            }                            
                        }
                    }
                }
            }
        }
        if(!trans){
            automato.add(auxList);
        }
    }

    private boolean keyWord(String word) {
        for (int i = 0; i < this.keys.length; i++) {
            if (word.equals(this.keys[i])) {
                return true;
            }
        }
        return false;
    }
}
