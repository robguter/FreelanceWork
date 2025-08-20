package com.sisterag.FreelanceWork.utiles

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.exifinterface.media.ExifInterface
import androidx.room.TypeConverter

import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.also
import kotlin.io.copyTo
import kotlin.io.use
import kotlin.text.split
import kotlin.toString

@RequiresApi(Build.VERSION_CODES.O)
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun getFormatDt(fecha: String): Date {
    val formatDt =
        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dfecha = formatDt.parse(fecha.toString())
    return dfecha
}

fun loadImageFromTempFile(tempFile: File, imageView: ImageView) {
    try {
        val bitmap = BitmapFactory.decodeFile(tempFile.absolutePath)
        imageView.setImageBitmap(bitmap)
    } catch (e: Exception) {
        // Handle the error (e.g., log it, display a placeholder)
        e.printStackTrace()
    }
}
fun MessageOwn(context: Context?, msj: String) {
    Toast.makeText(context, "" + msj, Toast.LENGTH_LONG).show()
}
@SuppressLint("Recycle")
fun getRealPathFromURI(context: Context, uri: Uri): String? {
    val returnCursor = context.contentResolver.query(uri, null, null,
        null, null)
    val nameIndex =  returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
    val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
    returnCursor.moveToFirst()
    val name = returnCursor.getString(nameIndex)
    val size = returnCursor.getLong(sizeIndex).toString()
    val file = File(context.filesDir, name)
    try {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        var read = 0
        val maxBufferSize = 1 * 1024 * 1024
        val bytesAvailable: Int = inputStream?.available() ?: 0
        //int bufferSize = 1024;
        val bufferSize = Math.min(bytesAvailable, maxBufferSize)
        val buffers = ByteArray(bufferSize)
        while (inputStream?.read(buffers).also {
                if (it != null) {
                    read = it
                }
            } != -1) {
            outputStream.write(buffers, 0, read)
        }
        Log.e("File Size", "Size " + file.length())
        inputStream?.close()
        outputStream.close()
        Log.e("File Path", "Path " + file.path)

    } catch (e: java.lang.Exception) {
        Log.e("Exception", e.message!!)
    }
    return file.path
}
fun getPathFromUri(context: Context, uri: Uri): String? {
    // Intenta obtener la ruta del archivo a través de ContentResolver
    val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
    if (inputStream != null) {
        try {
            val exifInterface = ExifInterface(inputStream)
            val filePath = exifInterface.getAttribute(ExifInterface.TAG_IMAGE_UNIQUE_ID)
            if (filePath != null) {
                return filePath
            }
        } catch (e: Exception) {
            // Manejar la excepción si no se puede acceder a la información EXIF
            e.printStackTrace()
        } finally {
            inputStream.close()
        }
    }
    // Si no se pudo obtener la ruta del archivo a través de ExifInterface,
    // intenta obtenerla a través de FileProvider
    val file = File(uri.path!!)
    if (file.exists()) {
        return file.absolutePath
    }else{
        // Si no se pudo obtener la ruta del archivo de ninguna manera, devuelve null
        return null
    }
}
fun fileFromContentUri(context: Context, uri: Uri): File? {
    var extns = getFileExtension(uri, context)
    if(extns == "jpeg") {
        extns = "jpg"
    }
    val tempFile = File.createTempFile(extns.toString(), null, context.cacheDir)
    try {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(tempFile)
        inputStream?.copyTo(outputStream)
        return tempFile
    } catch (e: Exception) {
        e.printStackTrace()
        tempFile.delete()
        return null
    }
}

fun getFileExtension(uri: Uri, context: Context): String? {
    val contentResolver: ContentResolver = context.contentResolver
    val mimeType = contentResolver.getType(uri)
    if (mimeType != null) {
        return mimeType.split("/")[1]
    }
    return null
}
fun View.snackbar(message: String) {
    Snackbar.make(
        this,
        message,
        Snackbar.LENGTH_LONG
    ).also { snackbar ->
        snackbar.setAction("ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun createImagePart(imageFile: File): MultipartBody.Part {
    val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile)
    return MultipartBody.Part.createFormData("image", imageFile.name, requestBody)
}

@SuppressLint("Range")
fun ContentResolver.getFileName(uri: Uri): String {
    var name: String = ""
    val cursor = query(
        uri, null, null,
        null, null
    )
    cursor?.use {
        it.moveToFirst()
        name = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
    return name
}