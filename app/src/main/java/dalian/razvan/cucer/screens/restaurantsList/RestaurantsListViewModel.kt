package dalian.razvan.cucer.screens.restaurantsList

import android.util.Log
import androidx.lifecycle.viewModelScope
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseViewModel
import dalian.razvan.cucer.core.baseClasses.RecyclerViewItemClickListener
import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.repository.restaurants.RestaurantsRepository
import dalian.razvan.cucer.models.restaurant.Category
import dalian.razvan.cucer.models.restaurant.Restaurant
import kotlinx.coroutines.launch

class RestaurantsListViewModel(var repository: RestaurantsRepository): BaseViewModel() {

    val restaurants = arrayListOf<Restaurant>()

    fun loadRestaurants(fragment: RestaurantsListFragmentView) {
        viewModelScope.launch {

            when(val response = repository.initRestaurants()) {
                is Result.Success -> {

                    val initRestaurantResponse = response.value
                    Log.e("json", "json body yo'\n " + response.toString())
                    initRestaurantResponse?.let {
                        if (it.isSuccessful) {
                            fragment.setCategoryList(it.restaurantsResponseData.restaurantCategories)
                            fragment.setRestaurantList(it.restaurantsResponseData.restaurantsResponseData.restaurants)
                        } else {
                            fragment.showPopup(it.message + "")
                        }
                    }
                }
                else -> {

                }
            }
        }
    }

    fun onCategoryItemClick(): RecyclerViewItemClickListener<Category> = object: RecyclerViewItemClickListener<Category> {
        override fun onItemClick(item: Category) {
            TODO("Not yet implemented")
        }
    }

    fun onRestaurantItemClick(): RecyclerViewItemClickListener<Restaurant> = object: RecyclerViewItemClickListener<Restaurant> {
        override fun onItemClick(item: Restaurant) {
            TODO("Not yet implemented")
        }
    }
}