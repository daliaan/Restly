package dalian.razvan.cucer.core.baseClasses

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T: BaseModel,VH : BaseRecyclerViewHolder<T>> : RecyclerView.Adapter<VH>(){

    protected var items = arrayListOf<T>()
    protected lateinit var onClickListener: View.OnClickListener

    protected abstract fun getItem(position : Int) : BaseModel

    fun addHolderOnClickListener(onClickListener: View.OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun setList(items: ArrayList<T>) {
        this.items.addAll(items)
    }

    fun resetList(items: ArrayList<T>) {
        this.items.clear()
        setList(items)
    }
}