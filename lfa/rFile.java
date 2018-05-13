/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lfa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class rFile {

    private String fileDir;
    private String fileOut;

    private String[] keys;
    private boolean trans = false;
    private boolean outFn = false;
    private int maxCont = 0;
    private String type;
    private List<ArrayList<String>> automato = new ArrayList<>();

    public rFile(String fileDir, String fileOut,String[] keys) {
        this.fileDir = fileDir;
        this.fileOut = fileOut;
        this.keys = keys;
    }

    public List<ArrayList<String>> readFile() {
        try {
            FileReader file = new FileReader(this.fileDir);
            BufferedReader readF = new BufferedReader(file);

            String line = readF.readLine();

            while (line != null) {
                line = line.replace("()", "[]");
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
                        if (token.equals("trans")) {
                            this.trans = true;
                        }
                        else if (token.equals("out-fn")) {
                            this.trans = false;
                            this.outFn = true;
                            this.maxCont = 2;
                            auxList.add(token);
                            this.automato.add(auxList);
                        }
                        else if (token.equals("moore")) {
                            this.type = "moore";
                            this.maxCont = 3;
                        }
                        else if (token.equals("mealy")) {
                            this.type = "mealy";
                            this.maxCont = 4;
                        }
                        if (!keyWord(token)) {
                            auxList.add(token);
                            cont++;
                            if ((trans && cont == this.maxCont) || (outFn && cont == this.maxCont)) {
                                automato.add(auxList);
                                auxList = new ArrayList<>();
                                cont = 0;
                            }
                        }
                    }
                }
            }
        }
        if (!trans && !outFn) {
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
    
    public void writeFile(List<ArrayList<String>> automato) throws IOException{
        FileWriter file = new FileWriter(this.fileOut);
        PrintWriter writer = new PrintWriter(file);
        int iKey = 0;
        boolean trans = false;
        boolean transValue = false;
        for(int i=0;i<automato.size();i++){
            trans = false;
            String line = "(";
            if(i == 0){
                line += automato.get(i).get(0);
            }
            else if (i > 0){
                if(iKey < this.keys.length-1){
                    line += this.keys[iKey];
                    if(this.keys[iKey].equals("trans")){
                        trans = true;
                        transValue = true;
                    }
                    iKey++;
                }
                if(!trans){
                    for(int j=0;j<automato.get(i).size();j++){
                        if(!transValue){
                            line+= " ";
                        }
                        line+= automato.get(i).get(j);
                        if(transValue && automato.get(i).size()-1 > j){
                            line+= " ";
                        }
                    }
                    if(!automato.get(i).get(0).equals("out-fn")){
                        line+= ")";
                    }
                }
            }
            if(i == automato.size()-1 || automato.get(i+1).get(0).equals("out-fn")){
                line+= ")";
            }
            if(i+1 == automato.size()){
                line+= ")";
            }
            line+="\n";
            writer.printf(line);
        }
        file.close();
    }
}
