package dalian.razvan.cucer.customViews.recyclerViews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseModel
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewAdapter
import dalian.razvan.cucer.models.restaurant.Category

class CategoryAdapter: BaseRecyclerViewAdapter<Category, CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder
        = CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_cell, parent, false))

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun getItem(position: Int): Category = items[position]
}