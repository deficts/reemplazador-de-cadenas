package com.deficts;

public class Arista {
    private char llave;
    private Estado inicio;
    private Estado fin;

    public Arista(char llave, Estado inicio, Estado fin){
        this.llave = llave;
        this.inicio = inicio;
        this.fin = fin;
    }

    public char getLlave(){
        return llave;
    }

    public Estado getFin() {
        return fin;
    }

    public Estado getInicio() {
        return inicio;
    }

    @Override
    public String toString() {
        return "Llave: " + llave;
    }
}
