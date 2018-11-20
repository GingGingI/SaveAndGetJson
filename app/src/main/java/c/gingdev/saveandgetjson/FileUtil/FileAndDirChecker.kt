package c.gingdev.saveandgetjson.FileUtil

import android.content.Context
import android.util.Log
import java.io.File

class FileAndDirChecker {

    val context: Context

    constructor(context: Context){
        this.context = context
    }

    fun ChkAndMakeJsonFile(filePath: String) {
        val txtm : String = ""
        try {
            var outStream = context.openFileOutput(filePath, Context.MODE_PRIVATE)
            outStream.write(txtm.toByteArray())
            outStream.close()
        }catch (e: Exception) {
            e.printStackTrace()
        }

        val file = File(context.filesDir, filePath)
        Log.e("f", file.absolutePath)
        Log.e("f", ""+file.isFile)
        Log.e("f", ""+file.isDirectory)
        Log.e("f", ""+file.exists())
    }

//    fun ChkAndMakeJsonFile(dir: File, fileName: String): File {
//        val JsonFile = makeFile(dir , fileName)
//        return JsonFile
//    }
}