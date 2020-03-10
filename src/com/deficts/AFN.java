package com.deficts;

public class AFN {

    private Estado inicial;
    private Estado actual;

    public AFN(){
        this.inicial = new Estado();
        this.actual = inicial;
    }

    public Estado getInicial() {
        return inicial;
    }

    public void quitarInicial(){
        inicial = null;
    }

    public Estado getActual() {
        return this.actual;
    }

    public void concatenacion(char x){
        Estado siguiente = new Estado();
        Arista a = new Arista(x,this.actual,siguiente);
        this.actual.addSalida(a);
        siguiente.addEntrada(a);
        siguiente.setFinal(true);
        this.actual.setFinal(false);
        this.actual = siguiente;
    }

    public Estado suma(Estado n){
        Estado e = new Estado();
        Arista epsilon = new Arista('?',n,e);
        n.addSalida(epsilon);
        e.addEntrada(epsilon);
        this.actual = e;
        return e;
    }

    public void cerradura(char x){
        this.actual = this.actual.desde(x);
        this.actual.setFinal(true);
        this.actual = this.actual.salto(x);
        Arista a = new Arista(x,this.actual,this.actual);
        this.actual.addEntrada(a);
        this.actual.addSalida(a);
    }
}
