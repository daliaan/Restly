package dalian.razvan.cucer.customViews.recyclerViews.restaurant

import android.view.View
import coil.load
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewHolder
import dalian.razvan.cucer.core.baseClasses.RecyclerViewItemClickListener
import dalian.razvan.cucer.models.restaurant.Restaurant
import kotlinx.android.synthetic.main.restaurant_cell.view.*

class RestaurantViewHolder(itemView: View): BaseRecyclerViewHolder<Restaurant>(itemView) {

    override fun bind(item: Restaurant, onItemClick: RecyclerViewItemClickListener<Restaurant>) {
        itemView.restaurant_image.load(item.imageUrl)
        itemView.restaurant_title.text = item.title
        itemView.restaurant_rating.text = item.rating.toString()
        itemView.restaurant_categories.text = item.categoriesString
        itemView.setOnClickListener {
            onItemClick.onItemClick(item)
        }
    }
}
