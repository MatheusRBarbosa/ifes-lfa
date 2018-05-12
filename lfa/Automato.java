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
    private List<ArrayList<String>> automatoOriginal;

    public Automato(List<ArrayList<String>> array) {
        this.automato = array;
        this.automatoOriginal = array;
    }

    public void imprime() {
        for (int i = 0; i < this.automato.size(); i++) {
            System.out.println(this.automato.get(i));
        }
    }

    public void convert() {
        if (this.automato.get(0).get(0).equals("moore")) {
            toMealy();
        } else if (this.automato.get(0).get(0).equals("mealy")) {
            toMoore();
        }
    }

    private void toMealy() {
        //0 - tipo || 1 - entrada || 2 - saidas || 3 - estados
        //4 - start || 5 - final || 6..n - Trans
        boolean outFind = false;
        int outPos = 0;
        for (int i = 0; i < this.automato.size(); i++) {
            if (outFind) {
                ArrayList<String> add = this.automato.get(i);
                for (int j = 6; j < this.automato.size() && !this.automato.get(j).get(0).equals("out-fn"); j++) {
                    if (this.automato.get(j).get(1).equals(add.get(0))) {
                        this.automato.get(j).add(add.get(1));
                    }
                }
            }
            if (this.automato.get(i).get(0).equals("out-fn")) {
                outFind = true;
                outPos = i;
            }
        }
        this.automato.remove(0);
        this.automato.add(0, new ArrayList<String>(){{add("mealy");}});
        for (int i = outPos; i < this.automato.size() && outFind;) {
            this.automato.remove(i);
        }
    }

    private void toMoore() {
        this.automato.add((this.automato.size()), new ArrayList<String>(){{add("out-fn");}});
        ArrayList<Integer> pos;
        for (int i = 0; i < this.automato.get(3).size(); i++) {
            pos = new ArrayList<>();
            for (int j = 6; j < this.automato.size() && !this.automato.get(j).get(0).equals("out-fn"); j++) {
                if (this.automato.get(3).get(i).equals(this.automato.get(j).get(1))) {
                    pos.add(j);  
                }
            }
            if(pos.size() == 0){
                migrateEstateZero(i);
            }
            else if (pos.size() == 1) {
                migrateEstate(pos.get(0));
            }
            else if (pos.size() > 1) {
                migrateEstate(pos);
            }
        }
    }

    private void migrateEstateZero(int pos){
        ArrayList<String> newOut = new ArrayList<>();
        String outfn = "()";
        newOut.add(this.automato.get(3).get(pos));
        newOut.add(outfn);
        this.automato.add(newOut);
    }
    
    private void migrateEstate(int pos) {
        ArrayList<String> newOut = new ArrayList<>();
        String outfn = this.automato.get(pos).get(3);
        newOut.add(this.automato.get(pos).get(1));
        newOut.add(outfn);
        this.automato.add(newOut);
        this.automato.get(pos).remove(3);
    }
    
    private void migrateEstate(ArrayList<Integer> pos){
        String newState = this.automato.get(pos.get(0)).get(1);
        int outSize = 0;
        ArrayList<String> newOut;
        for(int k=0;k<pos.size();k++){
            if(this.automato.get(pos.get(k)).size() == 4){
                /*for(int i=0;i<pos.size();i++){
                    if(this.automato.get(pos.get(i)).get(3).equals(outfn)){
                        this.automato.get(pos.get(i)).remove(3);
                    }
                }*/
            }
            if(outSize < this.automato.get(2).size()){
                newOut = new ArrayList<>();
                String outfn = this.automato.get(pos.get(k)).get(3);
                newOut.add(newState);
                newOut.add(outfn);
                this.automato.add(newOut);
                newState += "'";
                outSize++;
            }
        }
    }
}
