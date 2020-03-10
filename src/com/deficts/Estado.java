package com.deficts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Estado {
    private boolean esFinal = false;

    private List<Arista> salidas = new ArrayList<Arista>();
    private List<Arista> entradas = new ArrayList<Arista>();

    public void setFinal(boolean esFinal){
        this.esFinal= esFinal;
    }

    public boolean esFinal(){
        return this.esFinal;
    }

    public List<Arista> getSalidas() {
        return salidas;
    }
    public void addSalida(Arista a){
        this.salidas.add(a);
    }

    public List<Arista> getEntradas() {
        return entradas;
    }
    public void addEntrada(Arista a){
        this.entradas.add(a);
    }

    public boolean containsSalida(char x){
        for(Arista a : salidas){
            if (a.getLlave() == x){
                return true;
            }
        }
        return false;
    }

    public Estado salto(char x) {
        Estado nuevo = new Estado();

        for (Arista a : this.salidas) {
            if (a.getLlave() == x) {
                nuevo = a.getFin();
            }
        }
        return nuevo;
    }

    public void removeSalida(char x){
        Iterator<Arista> it = this.salidas.iterator();
        while(it.hasNext()){
            Arista a = it.next();
            if(a.getLlave()==x){
                it.remove();
            }
        }
    }

    public Estado desde(char x){
        Estado nuevo = new Estado();
        for(Arista a : this.entradas){
            if(a.getLlave() == x){
                nuevo = a.getInicio();
            }
        }
        return nuevo;
    }

}
