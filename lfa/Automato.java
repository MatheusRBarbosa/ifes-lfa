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
    
    
    public Automato (List<ArrayList<String>> array){
        this.automato = array;
        this.automatoOriginal = array;
    }
    
    public void imprime(){
        for(int i=0;i<this.automato.size();i++){
            System.out.println(this.automato.get(i));
        }
    }
    
    public void convert (){
        if(this.automato.get(0).get(0).equals("moore")){
            toMealy();
        }
        else if(this.automato.get(0).equals("mealy")){
            toMoore();
        }
    }

    private void toMealy() {
        //0 - tipo || 1 - entrada || 2 - saidas || 3 - estados
        //4 - start || 5 - final || 6..n - Trans
        boolean outFind = false;
        int outPos = 0;
        for(int i=0;i<this.automato.size();i++){
            if(outFind){
                ArrayList<String> add = this.automato.get(i);
                for(int j=6; j<this.automato.size() && !this.automato.get(j).get(0).equals("out-fn");j++){
                    if(this.automato.get(j).get(1).equals(add.get(0))){
                        this.automato.get(j).add(add.get(1));
                    }
                }
            }
            if(this.automato.get(i).get(0).equals("out-fn")){
                outFind = true;
                outPos = i;
            }
        }
        this.automato.remove(0);
        this.automato.add(0, new ArrayList<String>(){{add("mealy");}});
        for(int i=outPos;i<this.automato.size() && outFind;){
            this.automato.remove(i);
        }
    }

    private void toMoore() {
        //Todo
    }
}
