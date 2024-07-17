package com.netomix.calcauladora

import android.util.Log
import androidx.core.text.isDigitsOnly
import java.lang.Exception

class Utilidades {
    /**
     * Convierte una cadena a una Lista, identificando numeros
     * @param cadena
     * @return lista de String
     */
    fun convertirAlista( cadena:String):MutableList<String>{
        var lista = mutableListOf<String>()
        var i=0
        var aux=""
    try {
        var size=cadena.length
        while (i<size){
            Log.e("Index While",i.toString())
            if (cadena[i].isDigit()) {
                aux="";
                if(i>1){
                    if(cadena[i-1]=='-' &&cadena[i-2]=='('){
                        aux=cadena[i-1]+"";
                    }
                }
                    var j=i;
                try {
                    while (cadena[j].isDigit() ||cadena[j]=='.'){
                        Log.e("Index",j.toString())
                        aux+=cadena[j];
                        j++
                    }
                }catch (e:Exception){
                }
                i=j
                lista.add(aux)
                if(i>=size){
                    break
                }
            }
            else  {
                if(i>=1){
                    if(cadena[i]=='-' &&cadena[i-1]=='('){
                    }else{
                        lista.add(cadena[i]+"")
                    }
                }else{
                    lista.add(cadena[i]+"")
                }
                i++;
            }
        }
        if(lista[0]=="-"&&lista[0]!="("){
            if(esNumero(lista[1])){
                lista[0]=lista[0]+lista[1];
                lista.removeAt(1)
            }
        }
    }catch (e:Exception){
        Log.e("Erorr en lista",e .toString())
    }
        return  lista
    }

    /**
     * Cambia los simbolos de maneo y mayor igual a sus simbolos
     */
    fun reducir(cadena: String):String{
            var cadenaAux = cadena.replace("=>", "⋝")
            cadenaAux = cadenaAux.replace("<=", "≤")
            return cadenaAux
        }
    /**
     * Elimina los elemntos de una lista dada
     * @param lista: Lista de la cual se van a elimnar los elementos
     * @param index: Indice del cual se empezara a eliminar (incluyente)
     * @param size: Numeros de elemntos apartir del cual se va a empezar a eliminar
     * @sample: [4,5,2,1,5,4,1,3] index=1, size = 3 nueva Lista = [4,5,4,1,3]
     */
    fun eliminarElementos(lista:MutableList<String>,index:Int,size:Int):MutableList<String>{
       for (i in index.rangeTo(size)){
           lista.removeAt(index)
       }
        return  lista
}
    /**
     * Compruebe si un caractaer es un numero
     * @param caracter Caracter a comprobar
     * @return true si es un caracter y false si no lo es
     */
    private fun esNumero(cadena:String):Boolean{
        try {
            val number = cadena.toDouble()
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }

    /**
     * Comprueba si una cadena tiene parentesis balanceados
     * @param: Cadena a evaluar
     * @return Regresa true se esta balanceado y false si hay parentesis no pares o mal colocados
     */
    fun estaBalanceadoParentesis(cadena:String):Boolean{
        var  lista= mutableListOf<Char>()
        cadena.forEach { element->

            if(element == '('){
                lista.add(element)
            }
            if(element == ')'){
                if(lista.size==0){
                    return  false
                }
                if(lista[lista.size-1] == '('){
                    lista.removeLast()
                }
                else{
                    return false
                }
            }
        }
        return lista.size <= 0
    }

    /**
     * Valida si un numero esta correcto en una cadena
     * @param numero a evaluar
     * @return Regresa true si la cadena corresponde a un numero valido
     */
    fun esDecimal(numero: String): Boolean {
        val regex = """^-?\d+(\.\d+)?$""".toRegex()
        return regex.matches(numero)
    }

    /**
     * Valida si existen operadores reptido en la cadea
     * @param cadena
     * @return: Regresa true si existe un operador usado mas de una vez seguida
     */
    fun estaOperadorRepetido(cadena: String): Boolean {
        val regex = Regex("[\\-+\\*\\/=\\^%<>≠≤⋝&|]{2,}")

        val matchResult = regex.containsMatchIn(cadena)


        return matchResult
    }
    /**
     * Valida si hay un operador mal colocado antes de un )
     * @param cadena
     * @sample /) =true
     * @return: Regresa true si hay un operado antes de )
     */

        fun estaOperadorMalColocadoAntes(input: String): Boolean {
            val regex = Regex("[-+\\-\\*\\/\\^%<>=≠≤⋝&|]\\)")
            val matchResult = regex.containsMatchIn(input)

            return matchResult
        }
    /**
     * Valida si hay un operador mal colocado despues de un (
     * Ignora + y -
     * @param cadena
     * @sample (/ = true
     * @return: Regresa true si hay un operador despues de (
     */
    fun estaOperadorMalColocadoDespues(cadena: String): Boolean {
        val regex = Regex("\\([\\*\\^/%<>=≠≤⋝&|]")

        val matchResult = regex.containsMatchIn(cadena)

        return matchResult
    }
    fun contains(lista: MutableList<String>, elemnto: String): Boolean {
        for (subLista in lista) {
            if (elemnto in subLista) {
                return true
            }
        }
        return false
    }
}