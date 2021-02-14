package dalian.razvan.cucer.customViews.recyclerViews.allergens

import android.view.View
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewHolder
import dalian.razvan.cucer.core.baseClasses.RecyclerViewItemClickListener
import dalian.razvan.cucer.models.product.Allergen
import kotlinx.android.synthetic.main.allergen_cell.view.*

class AllergensViewHolder(itemView: View): BaseRecyclerViewHolder<Allergen>(itemView) {

    override fun bind(item: Allergen, onItemClick: RecyclerViewItemClickListener<Allergen>) {
        itemView.allergen.text = item.title
    }
}
