package com.netomix.calcauladora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private var isButtonPressed: Boolean = false
    private val buttonMap = mutableMapOf<String, Button>()
    private val handler = Handler()
    lateinit var labelInfo:TextView
    private val delayMillis = 200
    var calular:Calular=Calular()
    var utilidades:Utilidades= Utilidades()
    var expresionStr=""
    lateinit var btn_borrar:ImageButton
     lateinit var pantalla : EditText
    var teclado =Teclado(buttonMap)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addButtonToMap("1", R.id.btn_1)
        addButtonToMap("2", R.id.btn_2)
        addButtonToMap("3", R.id.btn_3)
        addButtonToMap("4", R.id.btn_4)
        addButtonToMap("5", R.id.btn_5)
        addButtonToMap("6", R.id.btn_6)
        addButtonToMap("7", R.id.btn_7)
        addButtonToMap("8", R.id.btn_8)
        addButtonToMap("9", R.id.btn_9)
        addButtonToMap("0", R.id.btn_0)
        addButtonToMap("(", R.id.btn_parentesis_a)
        addButtonToMap(")", R.id.btn_parentesis_b)
        addButtonToMap("%", R.id.btn_modulo)
        addButtonToMap("/", R.id.btn_entre)
        addButtonToMap("*", R.id.btn_por)
        addButtonToMap("-", R.id.btn_menos)
        addButtonToMap("+", R.id.btn_mas)
        addButtonToMap(".", R.id.btn_punto)
        addButtonToMap("potencia", R.id.btn_potencia)
        addButtonToMap("raiz", R.id.btn_raiz)
        //addButtonToMap("resultado", R.id.btn_resultado)
        addButtonToMap("<", R.id.btn_menor_que)
        addButtonToMap(">", R.id.btn_mayor_que)
        addButtonToMap("=", R.id.btn_igual)
        addButtonToMap("diferente", R.id.btn_diferente)
        addButtonToMap("NOT", R.id.btn_not)
        addButtonToMap("OR", R.id.btn_or)
        addButtonToMap("AND", R.id.btn_and)
        btn_borrar=findViewById<ImageButton>(R.id.btn_borrar)
        pantalla=findViewById(R.id.edit_text_resultado)
        pantalla.isEnabled=false
        teclado= Teclado(buttonMap)
        labelInfo=findViewById(R.id.textView_info)
        btn_borrar.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(view: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Se presionó el botón
                        isButtonPressed = true
                        repeatFunction()
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        // Se liberó el botón
                        isButtonPressed = false
                        handler.removeCallbacksAndMessages(null) // Detener las ejecuciones pendientes
                    }
                }
                return true
            }
        })
    }

    /**
     * Ejecuta la funcion de borrar muchas veces
     */
    private fun repeatFunction() {
        if (isButtonPressed) {
            // Ejecuta tu función aquí
            // Por ejemplo, puedes mostrar un mensaje o realizar una acción específica
            eliminarCaracter()

            // Programa la siguiente ejecución
            handler.postDelayed({ repeatFunction() }, delayMillis.toLong())
        }
    }

    /**
     * Accion del boton borrar
     */
    fun borrar(view: View){
        eliminarCaracter()
    }

    /**
     * Accion del boton cambiar teclado
     */
    fun cambiarTeclado(view: View){
            teclado.cambio()
    }

    /**
     * Accion de boton calcular
     */
    fun calcular(view:View){
        try {
            var validaciones=0;
            expresionStr=pantalla.text.toString();
            if(!utilidades.estaBalanceadoParentesis(expresionStr)){
                pantalla.setError("Parentesis mal colocados")


                validaciones++
            }
            if(utilidades.estaOperadorRepetido(expresionStr)){
                pantalla.setError("La operacion no es soportada")

                validaciones++
            }
            if(utilidades.estaOperadorMalColocadoAntes(expresionStr)){
                pantalla.setError("Revisas los signos antes de )")

                validaciones++
            }
            if (utilidades.estaOperadorMalColocadoDespues(expresionStr)){
                pantalla.setError("Revisas los signos despues de (")
                validaciones++
            }
    if(validaciones==0){
        Log.e("Cadena",expresionStr)
        var lista=utilidades.convertirAlista(expresionStr)
        Log.e("Lsita resultante",lista.toString())
        var respuesta=calular.resolver(lista)[0]
        expresionStr=respuesta
        Log.e("Respuesta",respuesta)
        if(respuesta.equals("1")){
            expresionStr="True"
        }
        else if(respuesta.equals("0")){
            expresionStr="False"
        }
        else if(respuesta.toDouble()%1==0.0){
            var valor=respuesta.toDouble().toInt()
            expresionStr=valor.toString()
        }else{

        }

        pantalla.setText(expresionStr)
        Log.e("Lista",lista.toString())
    }

        }catch (e:Exception){
            Log.e("Error",e.message.toString())
            pantalla.setText("Ocurrio un error")

        }

       // expresionStr=calular.resolver(lista as MutableList<String>)[0]
       // pantalla.setText(expresionStr)
    }
    /**
     * Accion de boton C
     */
    fun limpiar(view: View){
        expresionStr=""
        pantalla.setText("");
    }

    fun imprimir(){
        pantalla.setText(expresionStr);
    }
    private fun addButtonToMap(buttonName: String, buttonResourceId: Int) {
        val button = findViewById<Button>(buttonResourceId)
        button.setOnClickListener{
            var aux = teclado.escribir(button)
            pantalla.setError(null)
            if(aux=="="){
                try {
                    if(expresionStr.length>0){
                        Log.e("Size",expresionStr.length.toString())
                        if(expresionStr[expresionStr.length-1]=='<'){
                            eliminarCaracter()
                            aux="≤"
                        }
                        else if(expresionStr[expresionStr.length-1]=='>'){
                            eliminarCaracter()
                            aux="⋝"
                        }
                    }
                }catch (e:Exception){
                    Log.e("Error",e.message.toString())
                }


            }

            expresionStr+=aux
            pantalla.append(aux)
        }
        buttonMap[buttonName] = button
    }

    /**
     * Eliminacion de un caracter
     */
    fun eliminarCaracter(){
        Log.e("Boorar",expresionStr.length.toString())
        if(expresionStr.length>0){
            Log.e("Entro",expresionStr)
            expresionStr= expresionStr.substring(0,expresionStr.length-1);
            pantalla.setText(expresionStr);
            pantalla.setSelection(expresionStr.length)

        }
    }
    fun reemplazarCaracterEnIndice(cadena: String, indice: Int, nuevoCaracter: Char): String {
        if (indice < 0 || indice >= cadena.length) {
            // Manejar el caso de índice fuera de los límites de la cadena
            return cadena
        }

        val charArray = cadena.toCharArray() // Convierte la cadena en un arreglo de caracteres
        charArray[indice] = nuevoCaracter // Reemplaza el carácter en el índice especificado
        return String(charArray) // Convierte el arreglo de caracteres de nuevo a una cadena
    }
}