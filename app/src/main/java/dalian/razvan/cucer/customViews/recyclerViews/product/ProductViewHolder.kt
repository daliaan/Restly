package dalian.razvan.cucer.customViews.recyclerViews.product

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewHolder
import dalian.razvan.cucer.core.baseClasses.RecyclerViewItemClickListener
import dalian.razvan.cucer.customViews.recyclerViews.allergens.AllergensAdapter
import dalian.razvan.cucer.models.product.Allergen
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
        itemView.product_price.text = "$ " + item.price.toString()

        itemView.allergen_list.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        itemView.allergen_list.setHasFixedSize(false)
        val adapter = AllergensAdapter()
        adapter.addItemClickListener(object: RecyclerViewItemClickListener<Allergen>{
            override fun onItemClick(item: Allergen) {

            }
        })
        itemView.allergen_list.adapter = adapter
        adapter.resetList(adapter.convertStringsArray(item.allergens))
    }
}
