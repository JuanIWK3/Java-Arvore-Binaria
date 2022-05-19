/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arvorebinaria;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author Reis
 */
public class Arvore {
    private No raiz;
    private Scanner in = new Scanner(System.in);

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
        No esquerdo = escritaArvore(in);
        No direito = escritaArvore(in);
        return new No(valor, esquerdo, direito);
    }

    public static Arvore leituraBreadth(Scanner in) {
        Arvore arvore = new Arvore();
        arvore.setRaiz(escritaArvoreBreadth(in));
        return arvore;
    }

    private static No escritaArvoreBreadth(Scanner in) {
        Queue<No> fila = new LinkedList<>();
        System.out.println("valor da raiz: ");
        String s = in.next();
        if (s.equals("n")) {
            return null;
        }
        int valor = Integer.parseInt(s);

        No raiz = new No(valor, null, null);

        fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.remove();
            System.out.println("no " + atual.getValue() + " esq: ");
            String strEsq = in.next();
            int esqValue;
            if (!strEsq.equals("n")) {
                esqValue = Integer.parseInt(strEsq);
                atual.setEsq(new No(esqValue, null, null));
            } else {
                atual.setEsq(null);
            }
            System.out.println("no " + atual.getValue() + " dir: ");
            String strDir = in.next();
            int dirValue;
            if (!strDir.equals("n")) {
                dirValue = Integer.parseInt(strDir);
                atual.setDir(new No(dirValue, null, null));
            } else {
                atual.setDir(null);
            }

            if (atual.getEsq() != null) {
                fila.add(atual.getEsq());
            }

            if (atual.getDir() != null) {
                fila.add(atual.getDir());
            }
        }

        return raiz;
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

    public void addNode() {
        System.out.println("Inserir no nó: ");
        No parent = findNode(this.in.nextInt());
        if (parent == null) {
            System.out.println("Nó não encontrado");
            return;
        }
        System.out.println("Inserir valor: ");
        int value = this.in.nextInt();
        if (this.contem(value)) {
            System.out.println("Valor já existe");
            System.out.println();
            return;
        }
        if (parent.getEsq() == null) {
            parent.setEsq(new No(value, null, null));
        } else if (parent.getDir() == null) {
            parent.setDir(new No(value, null, null));
        } else {
            System.out.println("Nó já está cheio");
            System.out.println();
        }

        if (this.isBalanced()) {
            System.out.println("Arvore balanceada");
        } else {
            System.out.println("Arvore nao balanceada");
        }

        this.printBreathFirst();

        if (this.isBalanced()) {
            System.out.println("Arvore balanceada");
        } else {
            System.out.println("Arvore nao balanceada");
            No unbalancedNode = this.findUnbalancedNode();

            if (unbalancedNode == null) {
                System.out.println("Arvore balanceada");
            } else {
                System.out.println("Nó desbalanceado: " + unbalancedNode.getValue());
                System.out.println("Altura esquerda: " + this.altura(unbalancedNode.getEsq()));
                System.out.println("Altura direita: " + this.altura(unbalancedNode.getDir()));
                System.out.println("Balanceamento: " + this.getBalance(unbalancedNode));
            }

            int rightBalance;
            if (unbalancedNode.getDir() != null) {
                rightBalance = this.getBalance(unbalancedNode.getDir());
            } else {
                rightBalance = 0;
            }

            int leftBalance;
            if (unbalancedNode.getEsq() != null) {
                leftBalance = this.getBalance(unbalancedNode.getEsq());
            } else {
                leftBalance = 0;
            }

            if (Math.abs(rightBalance) > Math.abs(leftBalance)) {
                this.rotateLeft(unbalancedNode);
            } else {
                this.rotateRight(unbalancedNode);
            }

            this.printBreathFirst();

            if (this.isBalanced()) {
                System.out.println("Arvore balanceada");
            } else {
                System.out.println("Arvore nao balanceada");
            }

        }
    }

    public void rotateLeft(No node) {
        No right = node.getDir();
        No parent = this.findParent(node.getValue());

        if (parent == null) {
            this.raiz = right;
            node.setDir(null);
            right.setEsq(node);
        } else {
            if (parent.getEsq() == node) {
                parent.setEsq(right);
            } else {
                parent.setDir(right);
            }
            node.setDir(null);
            right.setEsq(node);
        }
    }

    public void rotateRight(No node) {
        No left = node.getEsq();
        No parent = this.findParent(node.getValue());

        if (parent == null) {
            this.raiz = left;
            node.setEsq(null);
            left.setDir(node);
        } else {
            if (parent.getDir() == node) {
                parent.setDir(left);
            } else {
                parent.setEsq(left);
            }
            node.setEsq(null);
            left.setDir(node);
        }
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
