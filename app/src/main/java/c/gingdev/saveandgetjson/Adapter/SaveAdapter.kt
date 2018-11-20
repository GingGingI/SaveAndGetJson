package c.gingdev.saveandgetjson.Adapter

import android.support.v13.view.DragStartHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import c.gingdev.saveandgetjson.Holder.SaveHolder
import c.gingdev.saveandgetjson.Models.ListItemModel
import c.gingdev.saveandgetjson.R
import c.gingdev.saveandgetjson.TouchHelper.ItemTouchHelperAdapter
import c.gingdev.saveandgetjson.TouchHelper.onStartDragListener
import java.util.*

class SaveAdapter: RecyclerView.Adapter<SaveHolder>, ItemTouchHelperAdapter {


    private val items: LinkedList<ListItemModel> = LinkedList()
    private val dragListener: onStartDragListener

    constructor(dragListener: onStartDragListener) {
        this.dragListener = dragListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.child_save_rc, parent, false)
        val vh = SaveHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SaveHolder, position: Int) {
        holder.title.setText(items.get(position).title)
        holder.title.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var item = ListItemModel()
                item.title = ""
                item.content = ""
                items.set(position, item)
            }
        })
        holder.content.setText(items.get(position).content)
        holder.view.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                if (event!!.action == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(holder)
                }
                return true
            }
        })
    }

    /**Add&GetListItem**/

    fun addItem(){
        var item: ListItemModel = ListItemModel()
        item.title = ""
        item.content = ""
        items.add(item)
        notifyItemInserted(items.size)
    }

    fun getItems(): LinkedList<ListItemModel> {
        return items
    }

    /**ItemTouchHelperAdapter**/

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }

}