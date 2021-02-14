package dalian.razvan.cucer.core.baseClasses

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T: BaseModel,VH : BaseRecyclerViewHolder<T>> : RecyclerView.Adapter<VH>(){

    protected var items = arrayListOf<T>()

    protected abstract fun getItem(position : Int) : BaseModel
    abstract fun addHolderOnClickListener(onClickListener: View.OnClickListener)
    abstract fun setList(items: ArrayList<T>)
}