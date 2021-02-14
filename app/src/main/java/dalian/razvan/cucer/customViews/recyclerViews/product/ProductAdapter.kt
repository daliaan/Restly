package dalian.razvan.cucer.customViews.recyclerViews.product

import android.view.LayoutInflater
import android.view.ViewGroup
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseModel
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewAdapter
import dalian.razvan.cucer.models.product.Product

class ProductAdapter: BaseRecyclerViewAdapter<Product, ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder
        = ProductViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_cell, parent, false))
}
