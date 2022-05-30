/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvorebinaria;

/**
 *
 * @author Reis
 */
public class No {
    private int value; // Valor guardado no n´o
    private No esq; // Filho esquerdo
    private No dir; // Filho direito

    // Construtor
    No(int v, No e, No d) {
        value = v;
        esq = e;
        dir = d;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the esq
     */
    public No getEsq() {
        return esq;
    }

    /**
     * @param esq the esq to set
     */
    public void setEsq(No esq) {
        this.esq = esq;
    }

    /**
     * @return the dir
     */
    public No getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(No dir) {
        this.dir = dir;
    }
}
