package c.gingdev.saveandgetjson.TouchHelper

import android.graphics.Canvas
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

class ItemTouchHelperCallback : ItemTouchHelper.Callback {

    private val adapter: ItemTouchHelperAdapter

    constructor(adapter: ItemTouchHelperAdapter) {
        this.adapter = adapter
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        //        Layout Manager에기반해 movement flag설
        if (recyclerView.layoutManager is GridLayoutManager) { // Grid
            //            드래그
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            //            스와이프
            val swipeFlags = 0
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        } else { // List
            //            드래그
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            //            스와이프
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            return ItemTouchHelper.Callback.makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    override fun onMove(recyclerView: RecyclerView, holder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        if (holder.itemViewType != target.itemViewType) {
            return false
        }

        //        어댑터에게 이동을알리기.
        adapter.onItemMove(holder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(holder: RecyclerView.ViewHolder, direction: Int) {
        //        어댑터에게 해당아이템삭제 알리기.
        if (direction == ItemTouchHelper.END) {
            //            오른쪽으로 했을때만.
            adapter.onItemDismiss(holder.adapterPosition)
        }
    }

    override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, holder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //            스와이프의 dX값을가져와 Alpha적용.
            if (dX > 0) {
                val alpha = ALPHA_FULL - Math.abs(dX) / holder.itemView.width.toFloat()
                holder.itemView.alpha = alpha
                holder.itemView.translationX = dX
            }
        } else {
            super.onChildDraw(c, recyclerView, holder, dX, dY, actionState, isCurrentlyActive)
        }

    }

    override fun onSelectedChanged(holder: RecyclerView.ViewHolder?, actionState: Int) {
        //        활성화된 아이템만 변경하면 되므로.
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (holder is ItemTouchHelperViewHolder) {
                //                뷰홀더에게 해당아이템이 선택되었음을 알리기.
                val itemHolder = holder as ItemTouchHelperViewHolder?
                itemHolder!!.onItemSelected()
            }
        }
        super.onSelectedChanged(holder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView?, holder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, holder)
        //        뷰돌려놓기.
        holder.itemView.alpha = ALPHA_FULL

        if (holder is ItemTouchHelperViewHolder) {
            val itemHolder = holder as ItemTouchHelperViewHolder
            itemHolder.onItemClear()
        }
    }

    companion object {

        val ALPHA_FULL = 1.0f
    }
}
