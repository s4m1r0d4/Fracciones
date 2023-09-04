/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author rc
 */
// https://espanol.lingolia.com/es/vocabulario/numeros-fechas-horas/numeros-cardinales
public class Cardinales
{

    public static String[] primeros = {
        "cero", "uno", "dos", "tres",
        "cuatro", "cinco", "seis", "siete",
        "ocho", "nueve", "diez", "once",
        "doce", "trece", "catorce", "quince",
        "dieciséis", "diecisiete", "dieciocho", "diecinueve",
        "veinte"
    };

    public static String[] decenas = {
        "cero", "diez", "veinte", "treinta", "cuarenta",
        "cincuenta", "sesenta", "setenta", "ochenta", "noventa"
    };

    public static String[] centenas = {
        "cero", "cien", "doscientos", "trescientos", "cuatrocientos",
        "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"
    };

    public static String toString(int n) throws Exception
    {
        if (n > 9801) {
            var msg = String.format(
                "[ERROR] Número máximo para Cardinales.toString() es 9801, se intentó usar %d",
                n);
            throw new Exception(msg);
        }

        var res = new StringBuilder();

        if (n < 0) {
            res.append("menos ");
            n = -n;
        }

        if (n < 100 && n % 10 == 0) {
            // decenas
            res.append(decenas[n / 10]);
            return res.toString();
        }

        if (n <= 20) {
            if (n == 1) res.append("un");
            else res.append(primeros[n]);
            return res.toString();
        } else if (n <= 30) {
            // Los números del 21 al 29 se escriben en una sola palabra.
            // Se forman combinando la veintena con la unidad correspondiente: veinte + y + uno/dos/tres...
            res.append("veinti");
            res.append(primeros[n % 10]);
            return res.toString();
        } else if (n < 100) {
            // Los números del 31 al 99 no se escriben en una sola palabra.
            // Se forman combinando la decena correspondiente y la unidad: 
            // treinta/cuarenta/cincuenta… + y + uno/dos/tres...
            res.append(decenas[n / 10]);
            res.append(" y ");
            res.append(primeros[n % 10]);
            return res.toString();
        } else if (n < 1000) {
            int cent = n / 100;
            int dec = (n / 10) % 10;
            int un = n % 10;

            res.append(centenas[cent]);
            if (cent == 1 && (dec != 0 || un != 0)) res.append("to");
            // ciento...

            if (!(dec == 0 && un == 0)) {
                var next = toString(n % 100);
                if (res.charAt(res.length() - 1) == next.charAt(0)) {
                    res.append(next.substring(1));
                } else {
                    res.append(next);
                }
            }

            return res.toString();
        } else {
            int mil = n / 1000;
            if (mil > 1) {
                // dos mil, tres mil, cuatro mil, ...
                res.append(primeros[mil]);
                res.append(' ');
            }
            res.append("mil ");
            var next = toString(n % 1000);
            res.append(next);

            return res.toString();
        }
    }
}
