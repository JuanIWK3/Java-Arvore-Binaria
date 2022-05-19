/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package arvorebinaria;

import java.util.Scanner;

/**
 *
 * @author Reis
 */
public class ArvoreBinaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // Arvore arvore = new Arvore();
        // No raiz = new No(1, null, null);
        // No no2 = new No(2, null, null);
        // No no3 = new No(3, null, null);
        // No no4 = new No(4, null, null);
        // No no5 = new No(5, null, null);
        // No no6 = new No(6, null, null);

        // arvore.setRaiz(raiz);
        // raiz.setEsq(no2);
        // raiz.setDir(no3);
        // no2.setEsq(no4);
        // no2.setDir(no5);
        // no3.setEsq(no6);
        // Arvore arvore = Arvore.leitura(in);
        Arvore arvore = Arvore.leituraBreadth(in);
        arvore.printBreathFirst();

        arvore.addNode();

        // arvore.printBreathFirst();
    }
}
