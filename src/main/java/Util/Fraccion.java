/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author rc
 */
public class Fraccion
{

    int num;
    int den;

    public static String[] numStrings = null;
    public static String[] denStrings = null;

    public Fraccion(int num, int den) throws Exception
    {
        if (num >= 1000) {
            var msg = String.format(
                "[ERROR] El numerador debe ser menor a mil, se intentó usar %d", num
            );
            throw new Exception(msg);
        }
        
        if (den >= 1000) {
            var msg = String.format(
                "[ERROR] El denominador debe ser menor a mil, se intentó usar %d", den
            );
            throw new Exception(msg);
        }
            
        this.num = num;
        this.den = den;
    }

    public Fraccion(String str) throws Exception
    {
        var palabras = str.toLowerCase().split(" ");
        if (palabras.length < 2) {
            throw new Exception("[ERROR] Las fracciones deben tener por lo menos dos palabras");
        }

        if (numStrings == null) genNumStrings();
        if (denStrings == null) genDenStrings();

        var numeradorBuilder = new StringBuilder();
        for (int i = 0; i < palabras.length - 1; ++i) {
            numeradorBuilder.append(palabras[i]);
            numeradorBuilder.append(' ');
        }
        var numerador = numeradorBuilder.toString().trim();

        // Encontrar numerador
        boolean found = false;
        for (int i = 0; i < numStrings.length; i++) {
            if (numStrings[i].equals(numerador)) {
                this.num = i;
                found = true;
                break;
            }
        }

        if (!found) {
            var msg = String.format("[ERROR] Numerador desconocido: %s\n", numerador);
            throw new Exception(msg);
        }

        // Encontrar denominador
        var denominador = palabras[palabras.length - 1];
        found = false;
        for (int i = 0; i < denStrings.length; i++) {
            String denString;

            if (num == 1) {
                denString = denStrings[i];
            } else {
                denString = denStrings[i] + 's';
            }

            if (denString.equals(denominador)) {
                this.den = i;
                found = true;
                break;
            }
        }

        if (!found) {
            var msg = String.format("[ERROR] Denominador desconocido: %s\n", denominador);
            throw new Exception(msg);
        }
    }

    private void genNumStrings()
    {
        numStrings = new String[1000];
        for (int i = 0; i < 1000; ++i) {
            try {
                numStrings[i] = Cardinales.toString(i);
            } catch (Exception e) {
                System.err.printf(
                    "[ERROR] genNumStrings() failed with input %d\n",
                    i
                );
            }
        }
    }

    private void genDenStrings()
    {
        denStrings = new String[1000];
        for (int i = 0; i < 1000; ++i) {
            try {
                denStrings[i] = Ordinales.toString(i);
            } catch (Exception e) {
                System.err.printf(
                    "[ERROR] genDenStrings() failed with input %d\n",
                    i
                );
            }
        }
    }

    @Override
    public String toString()
    {
        var res = new StringBuilder();

        try {
            res.append(Cardinales.toString(num));
            res.append(' ');
            res.append(Ordinales.toString(den));
            if (num != 1) res.append('s');
        } catch (Exception e) {
            System.out.println(e);
            System.err.printf(
                "[ERROR] Fraccion.toString() failed with num: %d and den: %d\n",
                num, den
            );
            return "Invalid";
        }

        return res.toString();
    }

    public int getNum()
    {
        return num;
    }

    public int getDen()
    {
        return den;
    }

    public Fraccion add(Fraccion other) throws Exception
    {
        int newNum = this.num * other.den + other.num * this.den;
        int newDen = this.den * other.den;
        if (newDen == 0) throw new Exception("[ERROR] El denominador no puede ser cero");
        return new Fraccion(newNum, newDen);
    }
    
    public Fraccion substract(Fraccion other) throws Exception
    {
        int newNum = this.num * other.den - other.num * this.den;
        int newDen = this.den * other.den;
        if (newDen == 0) throw new Exception("[ERROR] El denominador no puede ser cero");
        return new Fraccion(newNum, newDen);
    }
    
    public Fraccion multiply(Fraccion other) throws Exception
    {
        int newNum = this.num * other.num;
        int newDen = this.den * other.den;
        if (newDen == 0) throw new Exception("[ERROR] El denominador no puede ser cero");
        return new Fraccion(newNum, newDen);
    }
    
    public Fraccion divide(Fraccion other) throws Exception
    {
        int newNum = this.num * other.num;
        int newDen = this.den * other.den;
        if (newDen == 0) throw new Exception("[ERROR] El denominador no puede ser cero");
        return new Fraccion(newNum, newDen);
    }
}
