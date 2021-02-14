package dalian.razvan.cucer.customViews.recyclerViews.category

import android.view.View
import coil.load
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewHolder
import dalian.razvan.cucer.core.baseClasses.RecyclerViewItemClickListener
import dalian.razvan.cucer.models.restaurant.Category
import kotlinx.android.synthetic.main.category_cell.view.*

class CategoryViewHolder(itemView: View): BaseRecyclerViewHolder<Category>(itemView) {

    override fun bind(item: Category, onItemClick: RecyclerViewItemClickListener<Category>) {
        itemView.setOnClickListener {
            itemView.category_cell_background.isSelected = !itemView.category_cell_background.isSelected
            checkTitleColor(itemView.category_cell_background.isSelected)
            onItemClick.onItemClick(item)
        }
        itemView.category_cell_image.load(item.imageUrl)
        itemView.category_cell_title.text = item.title
        itemView.category_cell_background.isSelected = item.isSelected
        checkTitleColor(item.isSelected)
    }

    private fun checkTitleColor(selected: Boolean) {
        if (selected) {
            itemView.category_cell_title.setTextColor(itemView.resources.getColor(R.color.white))
        } else {
            itemView.category_cell_title.setTextColor(itemView.resources.getColor(R.color.dark_grey))
        }
    }
}
