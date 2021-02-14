package dalian.razvan.cucer.customViews.recyclerViews.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseRecyclerViewAdapter
import dalian.razvan.cucer.models.restaurant.Restaurant

class RestaurantAdapter: BaseRecyclerViewAdapter<Restaurant, RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder
        = RestaurantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.restaurant_cell, parent, false))
}
