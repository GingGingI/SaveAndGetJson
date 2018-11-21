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
//        파일 존재하는지 가져옮
        val file = File(context.filesDir, filePath)
        Log.e("f", file.absolutePath)
        Log.e("f", ""+file.isFile)
        Log.e("f", ""+file.isDirectory)
        Log.e("f", ""+file.exists())
//        파일이 없으면.
        if (!file.exists()) {
            try {
                val initText : String = ""
                var outStream = context.openFileOutput(filePath, Context.MODE_PRIVATE)
                outStream.write(initText.toByteArray())
                outStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

//    fun ChkAndMakeJsonFile(dir: File, fileName: String): File {
//        val JsonFile = makeFile(dir , fileName)
//        return JsonFile
//    }
}