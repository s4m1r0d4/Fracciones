/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.fracciones;

import Util.Cardinales;
import Util.Fraccion;
import Util.Ordinales;
import java.awt.SystemColor;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rc
 */
public class Fracciones
{

    public static Fraccion uno, dos;
    static Scanner scanner;

    public static void main(String[] args)
    {
        scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Número de fracciones: ");
        int t = scanner.nextInt();

        while (t-- > 0) {
            try {
                interpretaExpresion();
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
    }

    public static void interpretaExpresion() throws Exception
    {
        String input = scanner.next().toLowerCase();

        var fracciones = input.split("(mas|menos|por|entre)");
        if (fracciones.length < 2) {
            throw new Exception("[ERROR] No se ha ingresado un operador");
        }
        if (fracciones.length > 2) {
            throw new Exception("[ERROR] Se ha ingresado más de un operador");
        }

        uno = new Fraccion(fracciones[0]);
        dos = new Fraccion(fracciones[1]);

        Fraccion res;
        if (input.contains("mas")) {
            res = uno.add(dos);
        } else if (input.contains("menos")) {
            res = uno.substract(dos);
        } else if (input.contains("por")) {
            res = uno.multiply(dos);
        } else { // entre
            res = uno.divide(dos);
        }

        System.out.println(res);
    }
}
