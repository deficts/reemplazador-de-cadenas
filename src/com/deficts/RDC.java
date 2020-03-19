package com.deficts;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RDC {
    public static AFN crearAFN (String expReg){
        AFN afn = new AFN();
        Estado ramificacion = afn.getInicial();
        char ant = 0;
        int i = 0;
        Stack<Character> parentesis = new Stack<>();
        while(i<expReg.length()){
            if(expReg.charAt(i)=='('){
                parentesis.push('(');
                String dentroParentesis ="";
                int j = i+1;
                while(true){
                    if(expReg.charAt(j)==')'){
                        parentesis.pop();
                        if(parentesis.empty()){
                            break;
                        }
                    }else if(expReg.charAt(j)=='('){
                        parentesis.push('(');
                    }
                    if(!parentesis.empty()){
                        dentroParentesis+=expReg.charAt(j);
                    }
                    j++;
                }
                System.out.println("Grupo: "+dentroParentesis);
                AFN subAFN = crearAFN(dentroParentesis);
                for (Arista a : subAFN.getInicial().getSalidas()){
                    afn.getActual().addSalida(a);
                }
                subAFN.quitarInicial();
                i=j;
            }
            if(Character.isAlphabetic(expReg.charAt(i))){
                ant = expReg.charAt(i);
                afn.concatenacion(expReg.charAt(i));
            }else if(expReg.charAt(i)=='*'){
                afn.cerradura(ant);
            }else if(expReg.charAt(i)=='+'){
                ramificacion=afn.suma(ramificacion);
            }
            i++;
        }
        //System.out.println(afn.toString());
        return afn;
    }
    public static boolean comparar(String cadena, String expReg){
        boolean pertenece = false;
        AFN automata = crearAFN(expReg);
        Estado actual = automata.getInicial();
        if(actual.esFinal() && pertenece){
            return true;
        }
        char [] elementos = cadena.toCharArray();
        Estado temporal = new Estado();

        caracteres:
        for (char x : elementos){
            temporal = buscarAFN(actual,x);
            if(temporal==null){
                pertenece = false;
                break caracteres;
            }
            actual = temporal.salto(x);
            if(actual.esFinal()){
                pertenece=true;
                continue caracteres;
            }
            pertenece=false;
        }
        Estado ramificacion = automata.getInicial();
        ramas:
        while(ramificacion.containsSalida('?') && pertenece == false){
            actual = ramificacion.salto('?');
            temporal = new Estado();
            caracteres:
            for (char x : elementos){
                temporal = buscarAFN(actual,x);
                if(temporal==null){
                    pertenece = false;
                    break caracteres;
                }
                actual = temporal.salto(x);
                if(actual.esFinal()){
                    pertenece=true;
                    continue caracteres;
                }
                pertenece=false;
            }
            if(pertenece == true){
                break ramas;
            }
            ramificacion=ramificacion.salto('?');
        }
        return pertenece;
    }
    public static Estado buscarAFN(Estado q, char x){
        //System.out.println(x) ;
        if(q.containsSalida(x)){
            return q;
        }
        for (Arista a:q.getSalidas()) {
            if(a.getLlave()=='?'){
                return buscarAFN(a.getFin(),x);
            }
        }
        return null;
    }

    public static String nuevoString(String cadena, String expReg, String sustituto){
        int offset = sustituto.length();
        String ret="";
        for (int i = 0; i < cadena.length(); i++) {
            //System.out.println(i);
            String revisar = "";
            boolean hayExpresion = false;
            boolean fin = false;
            for(int j=i; j<cadena.length();j++) {
                revisar += cadena.charAt(j);
                //System.out.println(revisar);
                //System.out.println(comparar(revisar, expReg));
                if (comparar(revisar, expReg)) {
                    hayExpresion = true;
                }
                if (hayExpresion && !comparar(revisar, expReg)){
                    ret+=sustituto;
                    i=j-1;
                    break;
                }
                if(j+1==cadena.length()){
                    fin=true;
                }
            }
            if(!hayExpresion){
                ret+=cadena.charAt(i);
            }else if(hayExpresion && fin){
                ret+=sustituto;
                break;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(comparar("ana","arra+aca+ana"));
        /*Scanner in = new Scanner(System.in);
        while (true) {
        System.out.println("Ingresa la cadena: ");
        String cadena = in.nextLine();
        System.out.println("Ingresa la expresion regular: ");
        String expReg = in.nextLine();
        System.out.println("Ingresa el sustituto: ");
        String sust = in.nextLine();
        System.out.println("El resultado es: ");
        System.out.println(nuevoString(cadena, expReg, sust));
        }*/
    }
}
