package dalian.razvan.cucer.core.baseClasses

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T: BaseModel,VH : BaseRecyclerViewHolder<T>> : RecyclerView.Adapter<VH>(){

    protected var items = arrayListOf<T>()
    protected lateinit var itemClickListener: RecyclerViewItemClickListener<T>

    protected abstract fun getItem(position : Int) : BaseModel

    fun addItemClickListener(itemClickListener: RecyclerViewItemClickListener<T>) {
        this.itemClickListener = itemClickListener
    }

    fun setList(items: ArrayList<T>) {
        this.items.addAll(items)
    }

    fun resetList(items: ArrayList<T>) {
        this.items.clear()
        setList(items)
    }
}