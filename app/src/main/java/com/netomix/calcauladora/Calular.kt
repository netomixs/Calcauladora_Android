package com.netomix.calcauladora

import android.util.Log
import android.util.Range

class Calular {
         var operaciones:Operaciones=Operaciones()
     var utilidades: Utilidades=Utilidades()

    /**
     * Realiza las operaciones necesarias para simplificar la lista y llama a los metodos necesarios para evaluar las operaciones y resolverlas
     * @param lista Lista que contiene todos los valores y operaciones a rezliazar
     * @return Lista que contine los valores despues de ser simplificados
     */
    fun resolver(lista:MutableList<String>): MutableList<String> {
        var listaAux=lista
            if(!utilidades.contains(listaAux,"(")){
                if(listaAux.size>3){
                    listaAux[0]=aplicarJerarquia(listaAux)
                }
                if(listaAux.size==3){
                    listaAux[0]=operaciones.realizar(listaAux)
                    listaAux=utilidades.eliminarElementos(listaAux,1,2)

                }
                if(listaAux.size==2){
                        if(listaAux[0]=="!"){
                            listaAux[0]=operaciones.realizar(listaAux)
                            listaAux.removeAt(1)
                            return  listaAux
                        }
                    if(listaAux[0]=="-" ){
                        if(listaAux[1].contains("-")){
                            listaAux[0]=listaAux[1].substring(1);
                            listaAux.removeAt(1)

                        }else{
                            listaAux[0]=listaAux[0]+listaAux[1]
                            listaAux.removeAt(1)
                        }

                        return  listaAux
                    }
                }
            }
        else{
                if(listaAux.size==2){
                    return  listaAux
                }
            if(listaAux.size==3){
                listaAux.removeAt(2)
                listaAux.removeAt(0)
                Log.e("Final",listaAux.toString())
                return  listaAux
            }
            var start=0
                for (i in 0..listaAux.size-1){
                    if(listaAux[i].equals("(")){
                        start=i
                    }
                    if(listaAux[i].equals(")")){
                        try {
                            var subList=listaAux.subList(start+1,i)
                            Log.e("Nuea lista",subList.toString())
                              resolver(subList)
                            Log.e("  lista resultado",listaAux.toString())
                            listaAux.removeAt(start+2)
                            Log.e("  lista eliminar 1",listaAux.toString())
                            listaAux.removeAt(start)
                            Log.e("  lista eliminar 2",listaAux.toString())
                            return resolver(listaAux)
                         //   Log.e("Valor de i",i.toString())
                         //   Log.e("Lista antes",listaAux.toString())
                          //  Log.e("Resultado",resultado)
                            //listaAux[start]= resultado//Indecesaria por refernecia
                           // Log.e("Nuea lista despues del resultado",listaAux.toString())
                          //  listaAux.removeAt(start);
                           // listaAux.removeAt(i)
                          //  Log.e("NFinal",listaAux.toString())
                            break
                        }catch (e:Exception){
                            Log.e("Error Sublista",e.message.toString())
                        }

                        return resolver(listaAux)
                    }
                }
            }
            return  listaAux;

    }

    /**
     * Realiza la operaciones indicada en la lista de String
     * Se contempla que la lista dada sera una lista lineal y sin problemas de parentesis, sin operaciones de signo
     * @param lista Lista de String que contiene valores y operaciones pereparadas para resolver
     * @sample Ejemplo de lista validad: [1,+,4-1,*4]
     * @return Un string que contiene el resultado
     */
    fun aplicarJerarquia(lista:MutableList<String>):String {
        Log.e("  Lista  suma 1","Entoweindjewh")
        var listaAux = lista
        var i=0
        while (i<listaAux.size) {
            Log.e("  Divicion","Entoweindjewh")
            if (listaAux[i].equals("^")) {
                resolver(listaAux.subList(i - 1, i + 2))[0]
                i=i-2;
            }
            i++
        }
        i=0
        while (i<listaAux.size)  {
            Log.e("  Divicion","Entoweindjewh")
                if (listaAux[i].equals("*") || listaAux[i].equals("/")||listaAux[i].equals("%")) {
                   resolver(listaAux.subList(i - 1, i + 2))[0]
                    i=i-2;
                }
            i++
            }
        i=0
        while (i<listaAux.size)  {
            Log.e("  Lista  suma 1",listaAux.toString())
                if (listaAux[i].equals("+") || listaAux[i].equals("-")) {
                    if(i>0){
                         resolver(listaAux.subList(i - 1, i + 2))[0]
                        Log.e("  Lista suma",listaAux.toString())
                       i=i-2;
                    }
                    i++
                }
            if(listaAux.size==1){
                break
            }
            if(i>=listaAux.size){
                break
            }
            i++
            }


        i=0
        while (i<listaAux.size) {
                if (listaAux[i].equals(">") || listaAux[i].equals("<")|| listaAux[i].equals("=")||listaAux[i].equals("⋝")||listaAux[i].equals("≤")) {
                    if(i>0){
                     resolver(listaAux.subList(i - 1, i + 2))[0]
                        i=i-2;
                    }
                }
            i++
            }
        i=0
        while (i<listaAux.size) {
                if (listaAux[i].equals("&") || listaAux[i].equals("|")) {
                    if(i>0){
                        resolver(listaAux.subList(i - 1, i + 2))[0]
                        i=i-2;
                    }
                }
            i++
            }
        i=0
        while (i<listaAux.size) {
                if (listaAux[i].equals("!") ) {
                    if(i>0){
                        listaAux[i ] = resolver(listaAux.subList(i , i + 1))[0]
                        i=i-2;
                    }
                }
            i++
            }

        return  listaAux[0]
    }
}