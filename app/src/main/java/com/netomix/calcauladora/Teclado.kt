package com.netomix.calcauladora

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Switch

class Teclado {
     var teclado= mutableMapOf<String, Button>()
    var tipo=true;
    constructor(teclado: MutableMap<String, Button>){
        this.teclado=teclado
        aritmetica()
    }
    fun cambio(){
        if(tipo){
            logica()
            tipo=false
        }else{
            aritmetica()
            tipo=true
        }
    }
    /**
     * Cambia el teclado a su forma aritmetica
     */
    fun aritmetica(){
        tipo=true
        teclado["+"]?.visibility = View.VISIBLE
        teclado["-"]?.visibility = View.VISIBLE
        teclado["*"]?.visibility = View.VISIBLE
        teclado["/"]?.visibility = View.VISIBLE
        teclado["potencia"]?.visibility = View.VISIBLE
        teclado["raiz"]?.visibility = View.VISIBLE
        teclado["%"]?.visibility = View.VISIBLE

        teclado["<"]?.visibility = View.GONE
        teclado[">"]?.visibility = View.GONE
        teclado["="]?.visibility = View.GONE
        teclado["diferente"]?.visibility = View.GONE
        teclado["NOT"]?.visibility = View.GONE
        teclado["OR"]?.visibility = View.GONE
        teclado["AND"]?.visibility = View.GONE
    }

    /**
     * Cambia el teclado a la forma de entrada logica
     */
    fun logica(){
        tipo=false
        teclado["+"]?.visibility = View.GONE
        teclado["-"]?.visibility = View.GONE
        teclado["*"]?.visibility = View.GONE
        teclado["/"]?.visibility = View.GONE
        teclado["potencia"]?.visibility = View.GONE
        teclado["raiz"]?.visibility = View.GONE
        teclado["%"]?.visibility = View.GONE

        teclado["<"]?.visibility = View.VISIBLE
        teclado[">"]?.visibility = View.VISIBLE
        teclado["="]?.visibility = View.VISIBLE
        teclado["diferente"]?.visibility = View.VISIBLE
        teclado["NOT"]?.visibility = View.VISIBLE
        teclado["OR"]?.visibility = View.VISIBLE
        teclado["AND"]?.visibility = View.VISIBLE
    }

    /**
     *
     * @param button
     * @return: Retorna un caracter dependiendo el tipo de boton que se ingrese y este disponible dentro de la estructura
     */
    fun escribir(button:Button):String{
       when(button){
           teclado["("]->return "(";
           teclado[")"]->return ")";
           teclado["raiz"]->return "½";
           teclado["%"]->return "%";
           teclado["/"]->return "/";
           teclado["*"]->return "*";
           teclado["+"]->return "+";
           teclado["-"]->return "-";
           teclado["potencia"]->return "^";
           teclado["1"]->return "1";
           teclado["2"]->return "2";
           teclado["3"]->return "3";
           teclado["4"]->return "4";
           teclado["5"]->return "5";
           teclado["6"]->return "6";
           teclado["7"]->return "7";
           teclado["8"]->return "8";
           teclado["9"]->return "9";
           teclado["0"]->return "0";
           teclado["."]->return ".";
           teclado[">"]->return ">";
           teclado["<"]->return "<";
           teclado["="]->return "=";
           teclado["diferente"]->return "≠";
           teclado["OR"]->return "|";
           teclado["AND"]->return "&";
           teclado["NOT"]->return "!"

       }
        return  ""
    }

}