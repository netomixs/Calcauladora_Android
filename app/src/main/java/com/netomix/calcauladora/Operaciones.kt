package com.netomix.calcauladora

import android.util.Log
import java.lang.Exception
import kotlin.reflect.typeOf

class Operaciones {
    /**
     * Esta fucion se encarga de evaluar el tipo de operacion a realizar, y seleccionar la funcion requeridad para ello
     * @param lista: Una lista de 2 o 3 elemntos que contiene los elementos para ser realizada
     * @sample: Lista de 2 elemntos se toma por entendido que se trata de una negacion se espera [!,valor]
     * @sample: Lista de dos elemntos se toma por entendido que se trata de una operacion de dos valores. El operador debe de encontrarse siempre en el index 1 [1,+,1]
     * @sample: Se una de los calores resulta ser ½ se remplazara por el valor de 0.5. [100,^,½]->[100,^,0.5]
     *
     * @return Regresa un string con el valor del resultado de la operaciones [100,^,½]->"10"
     */
    fun realizar(lista:MutableList<String>):String{
        try {
            Log.e("Operacion",lista[0]+" "+lista[1]+" "+lista[2]   )
        }catch (e:Exception){
            Log.e("Operacion","Error"  )

        }

        if(lista.size==3){
            var b=0.0
            val a= lista[0].toDouble()
                if(lista[2].equals("½")){
                    Log.e("Medio",(0.5).toString())
                    b=(0.5).toDouble()
                }else{
                    b=lista[2].toString().toDouble()
                }
            when(lista[1]){
                "+"-> return Sumar(a,b)
                "-"-> return Restar(a,b)
                "*"-> return Multiplicar(a,b)
                "/"-> return Dividir(a,b)
                "^"-> return Potencia(a,b)
                "%"-> return Modulo(a,b)
                "<"-> return EsMenor(a,b)
                ">"-> return EsMayor(a,b)
                "≤"-> return EsMenorIgual(a,b)
                "⋝"-> return EsMayorIgual(a,b)
                "="-> return EsIgual(a,b)
                "&"-> return AND(a,b)
                "|"-> return OR(a,b)
                "≠"-> return EsDiferente(a,b)
            }
        }
        else{
            var s= lista[1].toString().toDouble()
            if(lista[0].toString().equals("!")){
                return NOT(s)
            }

        }
        return  ""
    }
    /**
     * Esta es una función que suma dos números.
     *
     * @param a El primer número a sumar.
     * @param b El segundo número a sumar.
     * @return La suma de los dos números.
     */
    fun Sumar(a: Double,b:Double):String {
        return (a+b).toString();
    }
    /**
     * Esta es una función que resta dos números.
     *
     * @param a El primer número a restar.
     * @param b El segundo número a restar.
     * @return La resta de los dos números.
     */
    fun Restar(a: Double,b:Double):String {
        return (a-b).toString();
    }
    /**
     * Esta es una función que multiplica dos números.
     *
     * @param a El primer número a multiplicar.
     * @param b El segundo número a multiplicar.
     * @return La multiplicacion de los dos números.
     */
    fun Multiplicar(a: Double,b:Double):String {
        return (a*b).toString();
    }
    /**
     * Esta es una función realiza la operacion @param a / @param b
     *
     * @param a El primer número a dividir.
     * @param b El segundo número a dividir.
     * @return La divicion de los dos números.
     */
    fun Dividir(a: Double,b:Double):String {
        return (a/b).toString();
    }
    /**
     * Esta es una función realiza la potencia de @param b a el valor @param a
     *
     * @param a Numero base.
     * @param b Potencia.
     * @return Resultado de la potencia.
     */
    fun Potencia(a: Double,b:Double):String {
        return Math.pow(a,b).toString();
    }
    /**
     * Esta es una función realiza el modulo de b al valor a
     *
     * @param a Numero base.
     * @param b Modulo que se quiere sacar.
     * @return Resultado de la potencia.
     */
    fun Modulo(a: Double,b:Double):String {
        return (a%b).toString();
    }

    /**Esta funcion realiza la comparacion de dos valores para saber si son iguaies
     * @param a: Valor uno a comparar
     * @param b: Valor dos a comparar
     * @return: Regresa 1 si son iguales y 0 si son difernetes
     */
    fun EsIgual(a: Double,b:Double):String {
            if(a.equals(b)){
                return "1";
            }
        else{
                return "0";
            }

    }
    /**Esta funcion realiza la comparacion de dos valores para saber si son difernetes
     * @param a: Valor uno a comparar
     * @param b: Valor dos a comparar
     * @return: Regresa 1 si son diferentes y 0 si son iguales
     *
     */
    fun EsDiferente(a: Double,b:Double):String {
        if(!a.equals(b)){
            return "1";
        }
        else{
            return "0";
        }

    }
    /**Esta funcion realiza la comparacion de dos valores para saber si a es mayor a b
     * @param a: Valor uno a comparar
     * @param b: Valor dos a comparar
     * @return: Regresa 1 si a es mayor a b
     *
     */
    fun EsMayor(a: Double,b:Double):String {
        if(a>b){
            return "1";
        }
        else{
            return "0";
        }
    }
    /**Esta funcion realiza la comparacion de dos valores para saber si a es menor a b
     * @param a: Valor uno a comparar
     * @param b: Valor dos a comparar
     * @return: Regresa 1 si a es menor a b
     *
     */
    fun EsMenor(a: Double,b:Double):String {
        if(a<b){
            return "1";
        }
        else{
            return "0";
        }
    }
    /**Esta funcion realiza la comparacion de dos valores para saber si a es mayor o igual a b
     * @param a: Valor uno a comparar
     * @param b: Valor dos a comparar
     * @return: Regresa 1 si a es mayor o igual a b
     *
     */
    fun EsMayorIgual(a: Double,b:Double):String {
        if(a>=b){
            return "1";
        }
        else{
            return "0";
        }
    }
    /**Esta funcion realiza la comparacion de dos valores para saber si a es menor o igual a b
     * @param a: Valor uno a comparar
     * @param b: Valor dos a comparar
     * @return: Regresa 1 si a es menor o igual a b
     *
     */
    fun EsMenorIgual(a: Double,b:Double):String {
        if(a<=b){
            return "1";
        }
        else{
            return "0";
        }
    }
    /**Esta funcion realiza la comparacion de dos valores y aplica la condicio OR
     * @param a: Valor uno a comparar
     * @param b: Valor dos a comparar
     * @return:Retorna 1 si cualquiera de los valores es 1
     * @return: Retorn 0 Si ningun valor es 1
     *
     */
    fun OR(a: Double,b:Double):String {
      if(a.equals(1.0)||b.equals(1.0)){
            return "1";
      }
        else{
            return "0";
      }
    }
    /**Esta funcion realiza la comparacion de dos valores y aplica la condicio OR
     * @param a: Valor uno a comparar
     * @param b: Valor dos a comparar
     * @return:Retorna 1 si cualquiera de los valores es 1
     * @return: Retorn 0 Si ningun valor es 1
     *
     */
    fun AND(a: Number,b:Number,):String {

        Log.e("And",a.toString().equals(1).toString());
        Log.e("And",b.toString().equals(1).toString());

        if(a.equals(1.0)&&b.equals(1.0) ){
            return "1";
        }
        else{
            return "0";
        }
    }
    fun NOT(a: Double ):String {
        if(a.equals(1.0) ){
            return "0";
        }
        else{
            return "1";
        }
    }
}