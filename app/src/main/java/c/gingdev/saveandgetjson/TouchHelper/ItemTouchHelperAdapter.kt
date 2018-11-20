package c.gingdev.saveandgetjson.TouchHelper

interface ItemTouchHelperAdapter {
    //    충분히 드래그해서 움직일경우 불려짐.
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    //    스와이프 취소시 불려짐.
    fun onItemDismiss(position: Int)
}
