package c.gingdev.saveandgetjson.JsonUtil

import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.*

class JsonReader {

    val context: Context

    constructor(context: Context) {
        this.context = context
    }

    fun getJsonArrayFromInternalStorage(): JSONArray {
        var jarr = JSONArray()

        try {
            val fis = context.openFileInput("JsonFiles.txt")
            val isr = InputStreamReader(fis, "UTF-8")
            val br = BufferedReader(isr)
            val sb = StringBuilder()

            br.readLine().forEach { line ->
                sb.append(line)
            }
            jarr = stringToJsonArray(sb.toString())

            br.close()
            isr.close()
            fis.close()

        }catch (fnfe: FileNotFoundException) {
        }catch (usee: UnsupportedEncodingException) {
        }catch (ie: IOException) {}

        return jarr
    }

    private fun stringToJsonArray(str: String): JSONArray {
        val jsonObject = JSONObject(str)
        return jsonObject.getJSONArray("data_d9b820b4-a9c7-49a1-9993-d1c714e928ac")
    }
}