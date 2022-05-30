package arvorebinaria;

import java.util.Scanner;

public class ArvoreBinaria {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Arvore arvore = Arvore.leitura(in);
        arvore.printBreathFirst();
        // arvore.addNode();
        arvore.addNode(in);
        arvore.printBreathFirst();
    }
}
