package c.gingdev.saveandgetjson.JsonUtil

import android.content.Context
import android.util.Log
import c.gingdev.saveandgetjson.FileUtil.FileAndDirChecker
import c.gingdev.saveandgetjson.Models.ListItemModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.*

class JsonMaker {
    private var arr: LinkedList<ListItemModel>

    private lateinit var jarr: JSONArray
    private lateinit var jobject: JSONObject

    private var context: Context

    constructor(context: Context, arr: LinkedList<ListItemModel>) {
        this.arr = arr
        this.context = context
        MakeJsonArr()
    }

    private fun MakeJsonArr() {
        jarr = JSONArray()
        for (position in 0..(arr.size - 1)) {
            jarr.put(getJsonObject(position))
        }
    }

    private fun getJsonObject(position: Int): JSONObject {
        jobject = JSONObject()

        jobject.put("title", arr.get(position).title)
        jobject.put("content", arr.get(position).content)

        return jobject
    }

    fun GetJsonArray(): JSONArray {
        return jarr
    }

    fun SaveJsonArrayAsFile() {
        val FADChecker = FileAndDirChecker(context)
        FADChecker.ChkAndMakeJsonFile("JsonFiles.txt")
        val fw = FileWriter(File(context.filesDir,"JsonFiles.txt"))
        try {
            fw.write(makeAnJsonObjectToString())
        }catch (ie: IOException) {
            ie.printStackTrace()
        }finally {
            fw.flush()
            fw.close()
        }
    }

    private fun makeAnJsonObjectToString(): String {
        val str = StringBuilder()
        str.append("{\"data_${UUID.randomUUID()}\" : [")

        for (position in 0..(jarr.length() - 1)) {
            str.append(jarr.getJSONObject(position).toString())

            if (position != jarr.length() - 1)
                str.append(",")
        }

        str.append("]}")
        Log.d("strbuilder", str.toString())
        return str.toString()
    }

}