package com.bandtec.br.finoban.models;

public class Pilha <T> {
    private int topo;
    private T[] pilha;

    public Pilha(int capacidade) {
        this.topo = -1;
        this.pilha = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return this.topo < 0;
    }

    public boolean isFull() {
        return this.pilha.length - 1 == this.topo;
    }

    public void push(T obj) {
        if (!this.isFull()) {
            this.pilha[++topo] = obj;
        }
    }

    public T pop() {
        if (!this.isEmpty()) {
            return this.pilha[topo--];
        }
        return null;
    }

    public T peek() {
        if (!this.isEmpty()) {
            return this.pilha[topo];
        }
        return null;
    }

    public void exibe() {
        if (this.isEmpty()) {
            System.out.println("Pilha vazia");
        }
        for (int i = topo; i >= 0; i--) {
            System.out.println(this.pilha[i]);
        }
    }

    public int getTopo() {
        return topo;
    }

    public T[] getPilha() {
        return pilha;
    }

}
