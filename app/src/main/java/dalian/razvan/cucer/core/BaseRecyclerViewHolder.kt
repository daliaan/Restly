package dalian.razvan.cucer.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<T: BaseModelView>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun setOnClickListener(onClickListener: View.OnClickListener?)
    abstract fun  bind(interfaceImplementation: T)
}