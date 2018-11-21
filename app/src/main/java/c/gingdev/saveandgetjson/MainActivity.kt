package c.gingdev.saveandgetjson

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import c.gingdev.saveandgetjson.Adapter.MainListAdapter
import c.gingdev.saveandgetjson.JsonUtil.JsonReader
import c.gingdev.saveandgetjson.Models.ListItemModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{


    lateinit var Rc: RecyclerView
    lateinit var AddBtn: Button
    lateinit var GetBtn: Button
    lateinit var adapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewInit()
    }

    private fun ViewInit() {
        Rc = RcView
        AddBtn = Add
        GetBtn = Get

        AddBtn.setOnClickListener(this)
        GetBtn.setOnClickListener(this)

        adapter = MainListAdapter()

        Rc.setHasFixedSize(true)
        Rc.setAdapter(adapter)
        Rc.setLayoutManager(LinearLayoutManager(this))
    }

    /**OnClickListener**/
    override fun onClick(v: View?) {
        when(v!!.id) {
            Add.id -> {
                val i = Intent(this, SaveJson::class.java)
                startActivity(i)
            }
            Get.id -> {
                val jr = JsonReader(this)
                val jarr = jr.getJsonArrayFromInternalStorage()
                for (i in 0..(jarr.length() - 1)) {
                    val jsonObject = jarr.getJSONObject(i)
                    val item = ListItemModel()
                    item.content = jsonObject.getString("content")
                    item.title = jsonObject.getString("title")
                    adapter.addItem(item)
                }
            }
        }
    }
}
