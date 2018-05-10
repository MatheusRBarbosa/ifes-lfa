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
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author mathe
 */
public class rFile {

    private String fileDir;

    private String[] keys;
    private boolean trans = false;
    private boolean outFn = false;
    private int maxCont = 0;
    private String type;
    private List<ArrayList<String>> automato = new ArrayList<>();

    public rFile(String fileDir, String[] keys) {
        this.fileDir = fileDir;
        this.keys = keys;
    }

    public List<ArrayList<String>> readFile() {
        try {
            FileReader file = new FileReader(this.fileDir);
            BufferedReader readF = new BufferedReader(file);

            String line = readF.readLine();

            while (line != null) {
                line = line.replace("()", "[]");
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
}
