package dalian.razvan.cucer.core.baseClasses

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<T: BaseModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun  bind(item: T, onItemClick: RecyclerViewItemClickListener<T>)
}