package c.gingdev.saveandgetjson.Holder

import android.graphics.Color
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import c.gingdev.saveandgetjson.R
import c.gingdev.saveandgetjson.TouchHelper.ItemTouchHelperViewHolder

class SaveHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {

    val title: EditText = itemView!!.findViewById(R.id.Title)
    val content: EditText = itemView!!.findViewById(R.id.Content)
    val view: LinearLayout = itemView!!.findViewById(R.id.View)

    override fun onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            itemView.elevation = 10f
        }
    }

    override fun onItemClear() {
        itemView.setBackgroundColor(0)
    }

}