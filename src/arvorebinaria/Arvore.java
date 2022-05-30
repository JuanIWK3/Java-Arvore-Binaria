/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvorebinaria;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Arvore {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private No raiz;

    Arvore() {
        raiz = null;
    }

    // M´etodo principal (p´ublico)
    public int numeroNos() {
        return numeroNos(raiz);
    }

    // M´etodo auxiliar (privado)
    public int numeroNos(No n) {
        if (n == null)
            return 0;
        return 1 + numeroNos(n.getEsq()) + numeroNos(n.getDir());
    }

    public int altura() {
        return altura(raiz);
    }

    private int altura(No n) {
        if (n == null) {
            return -1;
        }
        return 1 + Math.max(altura(n.getEsq()), altura(n.getDir()));
    }

    public boolean contem(int valor) {
        return contem(raiz, valor);
    }

    private boolean contem(No n, int valor) {
        if (n == null)
            return false;
        if (n.getValue() == valor)
            return true;

        return contem(n.getEsq(), valor) || contem(n.getDir(), valor);
    }

    public void printPreOrder() {
        System.out.print("PreOrder:");
        printPreOrder(raiz);
        System.out.println();
    }

    private void printPreOrder(No n) {
        if (n == null)
            return;
        System.out.print(" " + n.getValue());
        printPreOrder(n.getEsq());
        printPreOrder(n.getDir());
    }

    public void printInOrder() {
        System.out.print("InOrder:");
        printInOrder(raiz);
        System.out.println();
    }

    private void printInOrder(No n) {
        if (n == null)
            return;
        printInOrder(n.getEsq());
        System.out.print(" " + n.getValue());
        printInOrder(n.getDir());
    }

    public void printPostOrder() {
        System.out.print("PostOrder:");
        printPostOrder(raiz);
        System.out.println();
    }

    private void printPostOrder(No n) {
        if (n == null)
            return;
        printPostOrder(n.getEsq());
        printPostOrder(n.getDir());
        System.out.print(" " + n.getValue());
    }

    public static Arvore leitura(Scanner in) {
        Arvore arvore = new Arvore();
        arvore.setRaiz(escritaArvore(in));
        return arvore;
    }

    private static No escritaArvore(Scanner in) {
        String s = in.next();
        if (s.equals("n"))
            return null;
        int valor = Integer.parseInt(s);
        System.out.println(valor + " esq:");
        No esquerdo = escritaArvore(in);
        System.out.println(valor + " dir:");
        No direito = escritaArvore(in);
        return new No(valor, esquerdo, direito);
    }

    public void printBreathFirst() {
        System.out.println();
        System.out.println("BreathFirst:");
        printBreadthFirst(raiz);
        System.out.println();
    }

    private void printBreadthFirst(No node) {
        Queue<No> queue = new LinkedList<No>();
        queue.add(node);

        while (!queue.isEmpty()) {
            No current = queue.remove();
            System.out.println(current.getValue() + " " + getBalance(current));
            // System.out.println(current.getValue());

            if (current.getEsq() != null) {
                queue.add(current.getEsq());
            }
            if (current.getDir() != null) {
                queue.add(current.getDir());
            }
        }
    }

    public int getBalance(No node) {
        return this.altura(node.getDir()) - this.altura(node.getEsq());
    }

    public boolean isBalanced() {
        return isBalanced(raiz);
    }

    private boolean isBalanced(No node) {
        if (node == null) {
            return true;
        }

        int heightDiff = getBalance(node);

        if (Math.abs(heightDiff) > 1) {
            return false;
        }

        return isBalanced(node.getEsq()) && isBalanced(node.getDir());
    }

    public No findNode(int value) {
        if (!this.contem(value)) {
            return null;
        }
        return findNode(raiz, value);
    }

    private No findNode(No node, int value) {
        if (node == null) {
            return null;
        }
        if (node.getValue() == value) {
            return node;
        }
        No esq = findNode(node.getEsq(), value);
        if (esq != null) {
            return esq;
        }
        return findNode(node.getDir(), value);
    }

    public No findUnbalancedNode() {
        return findUnbalancedNode(raiz);
    }

    private No findUnbalancedNode(No node) {
        if (node == null) {
            return null;
        }
        if (Math.abs(getBalance(node)) > 1) {
            return node;
        }
        No esq = findUnbalancedNode(node.getEsq());
        if (esq != null) {
            return esq;
        }
        return findUnbalancedNode(node.getDir());
    }

    public void addNode(int value) {
        Scanner in = new Scanner(System.in);
        if (value >= 0) {
            addNode(raiz, value);
        } else {
            System.out.println("Digite o valor do novo nó: ");
            int invalue = in.nextInt();
            addNode(raiz, invalue);
        }
        in.close();
    }

    private void addNode(No node, int value) {
        if (node == null) {
            raiz = new No(value, null, null);
            return;
        }
        if (value < node.getValue()) {
            if (node.getEsq() == null) {
                node.setEsq(new No(value, null, null));
                printBreathFirst();
                if (!isBalanced()) {
                    if (node.getEsq().getValue() < node.getValue()) {
                        System.out.println("right rotation");
                        rotateRight(node, node.getEsq());
                    } else {
                        System.out.println("left-right rotation");
                        rotateLeft(node, node.getEsq());
                        rotateRight(node, node.getEsq());
                    }
                }
            } else {
                addNode(node.getEsq(), value);
            }
        } else if (value > node.getValue()) {
            if (node.getDir() == null) {
                node.setDir(new No(value, null, null));
                printBreathFirst();
                if (!isBalanced()) {
                    if (node.getDir().getValue() > node.getValue()) {
                        System.out.println("left Rotation");
                        rotateLeft(node, node.getDir());
                    } else {
                        System.out.println("right-left rotation");
                        rotateRight(node, node.getDir());
                        rotateLeft(node, node.getDir());
                    }
                }
            } else {
                addNode(node.getDir(), value);
            }
        } else {
            System.out.println(ANSI_YELLOW + "Valor já existe na árvore" + ANSI_RESET);
        }

    }

    private No rotateLeft(No node, No newNode) {
        No child = new No(node.getValue(), newNode.getEsq(), node.getDir());
        No parent = findParent(node.getValue());
        parent.setDir(null);
        No parentTemp = new No(parent.getValue(), parent.getEsq(), parent.getDir());

        parent.setValue(child.getValue());
        parent.setDir(newNode);
        parent.setEsq(parentTemp);

        return node;
    }

    private No rotateRight(No node, No newNode) {

        No child = new No(node.getValue(), newNode.getEsq(), node.getDir());
        No parent = findParent(node.getValue());
        parent.setEsq(null);
        No parentTemp = new No(parent.getValue(), parent.getEsq(), parent.getDir());

        parent.setValue(child.getValue());
        parent.setEsq(newNode);
        System.out.println(child.getValue());
        parent.setDir(parentTemp);

        return node;

    }

    public No findParent(int value) {
        if (!this.contem(value)) {
            return null;
        } else {
            return findParent(raiz, value);
        }
    }

    private No findParent(No node, int value) {
        if (node == null) {
            return null;
        }
        if (node.getValue() == value) {
            return null;
        }
        if (node.getEsq() != null && node.getEsq().getValue() == value) {
            return node;
        }
        if (node.getDir() != null && node.getDir().getValue() == value) {
            return node;
        }
        No esq = findParent(node.getEsq(), value);
        if (esq != null) {
            return esq;
        }
        return findParent(node.getDir(), value);
    }

    /**
     * @return the raiz
     */
    public No getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }

}
