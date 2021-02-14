package dalian.razvan.cucer.core.baseClasses

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dalian.razvan.cucer.R
import dalian.razvan.cucer.customViews.recyclerViews.category.CategoryViewHolder
import dalian.razvan.cucer.customViews.recyclerViews.restaurant.RestaurantViewHolder

abstract class BaseRecyclerViewAdapter<T: BaseModel,VH : BaseRecyclerViewHolder<T>> : RecyclerView.Adapter<VH>(){

    protected var items = arrayListOf<T>()
    protected lateinit var itemClickListener: RecyclerViewItemClickListener<T>

    protected fun getItem(position : Int) : T = items[position]

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    fun addItemClickListener(itemClickListener: RecyclerViewItemClickListener<T>) {
        this.itemClickListener = itemClickListener
    }

    fun setList(items: ArrayList<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun resetList(items: ArrayList<T>) {
        this.items.clear()
        setList(items)
    }
}