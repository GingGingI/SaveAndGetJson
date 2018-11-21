package c.gingdev.saveandgetjson.Holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import c.gingdev.saveandgetjson.R

class MainListHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView!!.findViewById(R.id.Title)
    val content: TextView = itemView!!.findViewById(R.id.Content)
}