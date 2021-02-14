package dalian.razvan.cucer.customViews.recyclerViews.category

import android.view.LayoutInflater
import android.view.ViewGroup
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewAdapter
import dalian.razvan.cucer.models.restaurant.Category

class CategoryAdapter: BaseRecyclerViewAdapter<Category, CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder
        = CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_cell, parent, false))
}