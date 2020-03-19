package com.deficts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interfaz extends JFrame implements ActionListener {

    static JTextField cadena,expresion,sustituto;
    static JButton btn;
    static JLabel indicacionCadena,indicacionExpresion,indicacionSustituto,resultado;

    public Interfaz(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void llenarInterfaz(){
        indicacionCadena = new JLabel("Ingresa una Cadena:");
        cadena = new JTextField(16);
        indicacionExpresion = new JLabel("Ingresa la Expresion:");
        expresion = new JTextField(16);
        indicacionSustituto = new JLabel("Ingresa el Sustituto:");
        sustituto = new JTextField(16);
        resultado = new JLabel();
        btn = new JButton("Ingresar");
        JPanel p = new JPanel();
        p.add(indicacionCadena);
        p.add(cadena);
        p.add(indicacionExpresion);
        p.add(expresion);
        p.add(indicacionSustituto);
        p.add(sustituto);
        p.add(resultado);
        p.add(btn);
        btn.addActionListener(this);
        this.add(p);
        this.setSize(300,300);
        this.show();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Ingresar")){
            resultado.setText(RDC.nuevoString(cadena.getText(),expresion.getText(),sustituto.getText()));
            this.repaint();
        }
    }

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        Interfaz i = new Interfaz();
        i.llenarInterfaz();
    }
}
