package dalian.razvan.cucer.screens.restaurantsList

import dalian.razvan.cucer.core.baseClasses.BaseFragmentView
import dalian.razvan.cucer.models.restaurant.Category
import dalian.razvan.cucer.models.restaurant.Restaurant

interface RestaurantsListFragmentView: BaseFragmentView {

    fun setCategoryList(list: ArrayList<Category>)
    fun setRestaurantList(list: ArrayList<Restaurant>)
    fun resetRestaurantList(restaurantList: ArrayList<Restaurant>)

    fun getQuery(): String
}
