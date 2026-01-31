/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hibernateapi;

import controlador.Controller;
import vista.Vista;

/**
 *
 * @author Óscar Ezquerro Sá
 */
public class Main {

    public static void main(String[] args) {
        Vista v = new Vista();
        Controller c = new Controller(v);
        v.setController(c);
        v.setVisible(true);
    }

}
