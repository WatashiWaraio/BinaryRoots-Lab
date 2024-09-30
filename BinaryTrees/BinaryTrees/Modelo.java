/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BinaryTrees;

/**
 *
 * @author qwerty2
 */
public class Modelo {
    private Comparable content; 
    private Modelo izq;
    private Modelo dere;

    public Modelo(Comparable content) {
        this.content = content;
        this.izq = null;
        this.dere = null;
    }

    public Comparable getContent() {
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
}

