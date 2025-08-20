package com.sisterag.FreelanceWork.utiles

import android.content.Context

import android.graphics.Bitmap
import android.graphics.BitmapFactory

import android.os.Environment

import android.widget.EditText
import android.widget.Toast

import androidx.constraintlayout.widget.ConstraintLayout

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.math.RoundingMode
import java.text.DecimalFormat


object EndPoint {

    const val BASE_URL: String = "https://my-json-server.typicode.com/robguter/apiphp/"
    // "http://172.16.0.8:8787/apifreelncr/"
    //"https://www.sisterag.com/ventas/" "http://172.16.0.8:8787/VentasSon/"

    const val IMG_URL: String = "https://my-json-server.typicode.com/robguter/apiphp/pubs/img/"
    //"http://172.16.0.8:8787/apifreelncr/pubs/img/"
    // "http://172.16.0.8:8787/VentasSon/pubs/img/"

    /** VENTA **/
    const val GAlCaliUrl: String = "calificacion/getAll/"
    const val GIdCaliUrl: String = "calificacion/getById/{caliid}"
    const val GRICaliUrl: String = "calificacion/getByRI/{calificadorid}"
    const val GOICaliUrl: String = "calificacion/getByOI/{calificadoid}"
    const val AddCaliUrl: String = "calificacion/insert/"
    const val ActCaliUrl: String = "calificacion/update/{caliid}"
    const val DelCaliUrl: String = "calificacion/delete/{caliid}"

    /** HABILIDAD **/
    const val GAlHablUrl: String = "habilidad/getAll/"
    const val GIdHablUrl: String = "habilidad/getById/{hablid}"
    const val GNmHablUrl: String = "habilidad/getByName/{nombre}"
    const val AddHablUrl: String = "habilidad/insert/"
    const val ActHablUrl: String = "habilidad/update/{hablid}"
    const val DelHablUrl: String = "habilidad/delete/{hablid}"

    /** HABILUSER **/
    const val GAlAblUUrl: String = "habiluser/getAll/"
    const val GIdAblUUrl: String = "habiluser/getById/{freeid}"
    const val GHIAblUUrl: String = "habiluser/getByHI/{hablid}"
    const val AddAblUUrl: String = "habiluser/insert/"
    const val ActAblUUrl: String = "habiluser/update/{freeid}"
    const val DelAblUUrl: String = "habiluser/delete/{freeid}"

    /** PAGO **/
    const val GAlPagoUrl: String = "pago/getAll/"
    const val GIdPagoUrl: String = "pago/getById/{pagoid}"
    const val GTIPagoUrl: String = "pago/getByTI/{trabid}"
    const val AddPagoUrl: String = "pago/insert/"
    const val ActPagoUrl: String = "pago/update/{pagoid}"
    const val DelPagoUrl: String = "pago/delete/{pagoid}"

    /** PROYECTO **/
    const val GAlProyUrl: String = "proyecto/getAll/"
    const val GIdProyUrl: String = "proyecto/getById/{proyid}"
    const val GNmProyUrl: String = "proyecto/getByName/{titulo}"
    const val AddProyUrl: String = "proyecto/insert/"
    const val ActProyUrl: String = "proyecto/update/{proyid}"
    const val DelProyUrl: String = "proyecto/delete/{proyid}"

    /** TRABAJO **/
    const val GAlTrabUrl: String = "trabajo/getAll/"
    const val GIdTrabUrl: String = "trabajo/getById/{trabid}"
    const val GPITrabUrl: String = "trabajo/getByPI/{proyid}"
    const val GFITrabUrl: String = "trabajo/getByFI/{freeid}"
    const val AddTrabUrl: String = "trabajo/insert/"
    const val ActTrabUrl: String = "trabajo/update/{trabid}"
    const val DelTrabUrl: String = "trabajo/delete/{trabid}"

    /** USUARIO **/
    const val GAlUserUrl: String = "usuario/"
    const val GIdUserUrl: String = "usuario/getById/{id}"
    const val GNmUserUrl: String = "usuario/getByName/{nombre}"
    const val AddUserUrl: String = "usuario/create/"
    const val ActUserUrl: String = "usuario/update/{id}"
    const val DelUserUrl: String = "usuario/delete/{id}"

    fun verdad (bTrue: Boolean): Int {
        return when(bTrue) {
            true-> {
                1
            }
            else -> {
                0
            }
        }
    }
    fun limpiarCampos(ll: ConstraintLayout, context: Context?) {
        val count = ll.childCount    // Obtiene el numero de EditText que contiene el layout
        for (i in 0 until count) {
            // En cada iteración obtienes uno de los editText que se encuentran el layout.
            val et :EditText
            val oObj = ll.getChildAt(i)
            if (oObj is EditText) {
                et = oObj
                et.setText("")
            }
        }
    }
    fun comprueba(ll: ConstraintLayout, context: Context?): Boolean {
        val count = ll.childCount    // Obtiene el numero de EditText que contiene el layout
        var isAllFill = true    // variable isAllFill a false, lo que indica que aun hay editText vacios.
        for (i in 0 until count) {
            // En cada iteración obtienes uno de los editText que se encuentran el layout.
            val et :EditText
            val oObj = ll.getChildAt(i)
            if (oObj is EditText) {
                et = oObj
                if (et.text.toString().isEmpty()) {
                    isAllFill = false
                    break
                }
            }
        }
        if(!isAllFill) {
            Toast.makeText(
                context, "Hay Campos Vacios",
                Toast.LENGTH_LONG
            ).show()
        }
        return isAllFill
    }

    fun BuscaEspacio(string: String): String {
        val s = string.split(' ')[0].trim()
        return s.trim()
    }
    fun formatNum(num: Double): String {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(num)
    }
    fun getImageFromStorage(context: Context, imagePath: String): Bitmap? {
        return try {
            val inputStream: InputStream = context.assets.open(imagePath)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
    fun saveImageToStorage(context: Context, bitmap: Bitmap, filename: String): File? {
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File(directory, filename)
        return try {
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
            fileOutputStream.close()
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}