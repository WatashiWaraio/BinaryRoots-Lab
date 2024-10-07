/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Huffman;

import BinaryTrees.*;
import java.awt.List;

/**
 *
 * @author qwerty2
 */
public class Modelo {
    private String letra;
    private int content; 
    private Modelo izq;
    private Modelo dere;

    public Modelo(int content, String letra) {
        this.letra = letra;
        this.content = content;
        this.izq = null;
        this.dere = null;
    }

    public int getContent() {
        return content;
    }

    public void setizq(Modelo izq) {
        this.izq = izq;
    }

    public void setdere(Modelo dere) {
        this.dere = dere;
    }

    public Modelo getizq() {
        return izq;
    }

    public Modelo getdere() {
        return dere;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public String getLetra() {
        return letra;
    }
   
}
