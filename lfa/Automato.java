/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lfa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mathe
 */
public class Automato {

    private List<ArrayList<String>> automato;
    private int endMealy;
    private int newTrans = 0;

    public Automato(List<ArrayList<String>> array) {
        this.automato = array;
    }

    public List<ArrayList<String>> getAutomato(){
        return this.automato;
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
        this.endMealy = this.automato.size();
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
        this.automato.add(0, new ArrayList<String>(){{add("moore");}});
        this.automato.remove(1);
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
        ArrayList<String> line;
        HashMap<String, String> outfn = new HashMap<>();
        ArrayList<String> newOut;
        
        outfn.put(this.automato.get(pos.get(0)).get(3), newState);
        for(int k=0;k<pos.size();k++){
            line = this.automato.get(pos.get(k));
            if(line.size() > 3){
                if(!outfn.containsKey(line.get(3))){
                    newState += "'";
                    outfn.put(line.get(3), newState);
                }
                /*if(line.get(0).equals(line.get(1))){
                    this.newTrans = 0;
                    newOut = new ArrayList<>();
                    newOut.add(outfn.get(line.get(3)));
                    newOut.add(outfn.get(line.get(3)));
                    newOut.add(this.automato.get(1).get(this.newTrans));
                    this.automato.add(this.endMealy, newOut);
                    this.endMealy++;
                    this.newTrans++;
                }*/
                line.add(1, outfn.get(line.get(3)));
                line.remove(2);
                line.remove(3);
                //System.out.println(line);
            }
        }
        for(String key: outfn.keySet()){
            newOut = new ArrayList<>();
            newOut.add(outfn.get(key));
            newOut.add(key);
            this.automato.add(newOut);
        }
        this.automato.remove(5);
        newOut = new ArrayList<>();
        for(String key: outfn.keySet()){
            newOut.add(outfn.get(key));
        }
        this.automato.add(5, newOut);
    }
}
