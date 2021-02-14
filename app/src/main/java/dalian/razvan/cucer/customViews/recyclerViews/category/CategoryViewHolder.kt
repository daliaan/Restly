package dalian.razvan.cucer.customViews.recyclerViews.category

import android.view.View
import coil.load
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewHolder
import dalian.razvan.cucer.core.baseClasses.RecyclerViewItemClickListener
import dalian.razvan.cucer.models.restaurant.Category
import kotlinx.android.synthetic.main.category_cell.view.*

class CategoryViewHolder(itemView: View): BaseRecyclerViewHolder<Category>(itemView) {

    override fun bind(item: Category, onItemClick: RecyclerViewItemClickListener<Category>) {
        itemView.setOnClickListener {
            onItemClick.onItemClick(item)
        }
        itemView.category_cell_image.load(item.imageUrl)
        itemView.category_cell_title.text = item.title
    }

}
