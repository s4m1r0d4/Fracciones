/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author rc
 */

// https://espanol.lingolia.com/es/vocabulario/numeros-fechas-horas/fracciones
// https://www.reglasdeortografia.com/numerales.html

public class Ordinales
{
    public static String[] primeros = {
        "ceravo", "entero", "medio", "tercio",
        "cuarto", "quinto", "sexto", "séptimo",
        "octavo", "noveno", "décimo"
    };
    
    public static String toString(int n) throws Exception
    {
        if (n > 9801) {
            var msg = String.format(
                "[ERROR] Número máximo para Ordinales.toString() es 9801, se intentó usar %d",
                n);
            throw new Exception(msg);
        }
        
        if (n < 11) {
            return primeros[n];
        }
        
        var cardinal = Cardinales.toString(n);
        
        // Si el denominador es un número formado por varias palabras, en las fracciones se escribe 
        // todo en una sola. Para ello, la conjunción y se convierte en i. En el caso de que se repitan 
        // dos vocales idénticas se suprime una.
        
        var res = new StringBuilder();
        var palabras = cardinal.split(" ");
        
        String prev = null;
        for (String palabra : palabras) {
            if (prev != null) {
                if (prev.charAt(prev.length() - 1) == palabra.charAt(0)) {
                    res.append(palabra.substring(1));
                } else {
                    if (palabra.equals("y")) res.append("i");
                    else res.append(palabra);
                }
            } else {
                res.append(palabra);
            }
            prev = palabra;
        }
        
        if (res.charAt(res.length() - 1) != 'a') res.append('a');
        res.append("vo");
        
        return res.toString();
    }
}
