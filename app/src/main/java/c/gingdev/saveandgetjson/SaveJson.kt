package c.gingdev.saveandgetjson

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Adapter
import android.widget.Button
import c.gingdev.saveandgetjson.Adapter.SaveAdapter
import c.gingdev.saveandgetjson.JsonUtil.JsonMaker
import c.gingdev.saveandgetjson.TouchHelper.ItemTouchHelperCallback
import c.gingdev.saveandgetjson.TouchHelper.onStartDragListener
import kotlinx.android.synthetic.main.child_save_rc.*
import kotlinx.android.synthetic.main.save_rc.*

class SaveJson : AppCompatActivity(), View.OnClickListener, onStartDragListener {


    lateinit var Rc: RecyclerView
    lateinit var AddBtn: Button
    lateinit var SaveBtn: Button
    lateinit var adapter: SaveAdapter
    lateinit var itHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.save_rc)

        ViewInit()
    }

    private fun ViewInit() {
        Rc = RcView
        AddBtn = Add
        AddBtn.setOnClickListener(this)
        SaveBtn = Save
        SaveBtn.setOnClickListener(this)

        adapter = SaveAdapter(this)

        Rc.setHasFixedSize(true)
        Rc.setAdapter(adapter)
        Rc.setLayoutManager(LinearLayoutManager(this))

        val callback = ItemTouchHelperCallback(adapter)
        itHelper = ItemTouchHelper(callback)
        itHelper.attachToRecyclerView(Rc)
    }


    /**OnClickListener**/
    override fun onClick(v: View?) {
        when(v!!.id) {
            Add.id -> {
                adapter.addItem()
            }
            Save.id -> {
                val jm = JsonMaker(this, adapter.getItems())
                jm.SaveJsonArrayAsFile()
            }
        }
    }
    /**onStartDragListener**/
    override fun onStartDrag(holder: RecyclerView.ViewHolder) {
        itHelper.startDrag(holder)
    }

}