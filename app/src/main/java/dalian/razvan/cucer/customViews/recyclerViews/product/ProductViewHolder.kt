package dalian.razvan.cucer.customViews.recyclerViews.product

import android.view.View
import coil.load
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewHolder
import dalian.razvan.cucer.core.baseClasses.RecyclerViewItemClickListener
import dalian.razvan.cucer.models.product.Product
import kotlinx.android.synthetic.main.product_cell.view.*
import kotlinx.android.synthetic.main.restaurant_cell.view.*

class ProductViewHolder(itemView: View): BaseRecyclerViewHolder<Product>(itemView) {

    override fun bind(item: Product, onItemClick: RecyclerViewItemClickListener<Product>) {
        itemView.restaurant_image.load(item.imageUrl)
        itemView.restaurant_title.text = item.title
        itemView.restaurant_rating.text = item.rating.toString()
        itemView.restaurant_categories.text = item.description
        itemView.setOnClickListener {
            onItemClick.onItemClick(item)
        }
        itemView.product_price.text = item.price.toString()

    }
}
