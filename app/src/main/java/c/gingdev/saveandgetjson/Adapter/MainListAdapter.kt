package c.gingdev.saveandgetjson.Adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.gingdev.saveandgetjson.Holder.MainListHolder
import c.gingdev.saveandgetjson.Models.ListItemModel
import c.gingdev.saveandgetjson.R
import java.util.*

class MainListAdapter: RecyclerView.Adapter<MainListHolder>() {

    private val items: LinkedList<ListItemModel> = LinkedList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.child_mainlist_rc, parent, false)
        val vh = MainListHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainListHolder, position: Int) {
        holder.title.setText(items.get(position).title)
        holder.content.setText(items.get(position).content)
    }

    /**ListItemController**/

    fun addItem(item: ListItemModel){
        items.add(item)
        notifyItemInserted(items.size)
    }

    fun getItems(): LinkedList<ListItemModel> {
        for (i in items) {
            Log.i("tt", i.title)
            Log.i("cc", i.content)
        }
        return items
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }
}