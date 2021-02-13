package dalian.razvan.cucer.screens.restaurantsList

import android.util.Log
import androidx.lifecycle.viewModelScope
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseViewModel
import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.repository.restaurants.RestaurantsRepository
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
                            var categoryNames = ""
                            for (i in 0 until it.restaurantsResponseData.restaurantCategories.size) {
                                categoryNames += it.restaurantsResponseData.restaurantCategories[i].title
                            }
                            fragment.showPopup(categoryNames)
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
}