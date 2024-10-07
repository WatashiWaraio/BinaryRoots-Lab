/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Huffman;

import Huffman.*;
import static java.lang.Math.max;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author qwerty2
 */
public class Controlador {
    
    private TreePanel treePanel; 
    private Modelo root;
    
    public Controlador(TreePanel treePanel) {
       this.treePanel = treePanel;
    }

    public Modelo getRoot() {
        return root; 
    }

    public void setRoot(Modelo root) {
        this.root = root;
    }
    
    public void huffman(String texto){
        ArrayList<Modelo> arboles = new ArrayList<>();
        int conteo;
        char x;
        Modelo nodo;
        while(texto!="") {
            x=texto.charAt(0);
            conteo=0;
            for (int j = 0; j < texto.length(); j++) {
                if(texto.charAt(j)==x){
                    conteo++;
                }
            }
            nodo=new Modelo(conteo,String.valueOf(x));
            arboles.add(nodo);
            texto=texto.replace(String.valueOf(x), "");
        }
        while(arboles.size()!=1){
            Modelo min1=arboles.get(0);
            Modelo min2=arboles.get(1);
            for (int i = 2; i < arboles.size(); i++) {
                if (arboles.get(i).getContent()<min1.getContent()){
                    min1=arboles.get(i);
                }else if(arboles.get(i).getContent()<min2.getContent()){
                    min2=arboles.get(i);
                }
            }
            Modelo nuevo=new Modelo(min1.getContent()+min2.getContent(),"");
            nuevo.setizq(min1);
            nuevo.setdere(min2);
            arboles.remove(min1);
            arboles.remove(min2);
            arboles.add(nuevo);
        }
        setRoot(arboles.get(0));
    }
   public int altura(){
        return alturaRecursivo(getRoot());
    }
    public int alturaRecursivo(Modelo nodo){
        if(nodo==null){
            return 0;
        }
        return 1+max(alturaRecursivo(nodo.getdere()),alturaRecursivo(nodo.getizq()));
    }
}
