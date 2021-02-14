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
import java.util.ArrayList

class RestaurantsListViewModel(private var repository: RestaurantsRepository): BaseViewModel() {

    private var reset = true
    private val selectedCategories = arrayListOf<Category>()

    fun loadRestaurants(fragment: RestaurantsListFragmentView) {
        viewModelScope.launch {

            if (repository.getRestaurantList().size > 0) {
                getRestaurants(fragment)
            } else {
                initRestaurants(fragment)
            }
        }
    }

    private fun initRestaurants(fragment: RestaurantsListFragmentView) {
        viewModelScope.launch {
            when (val response = repository.initRestaurants()) {
                is Result.Success -> {
                    val initRestaurantResponse = response.value
                    initRestaurantResponse?.let {
                        if (it.isSuccessful) {
                            repository.setCategoryList(it.restaurantsResponseData.restaurantCategories)
                            repository.resetRestaurantList(it.restaurantsResponseData.restaurantsResponseData.restaurants)

                            repository.setCurrentPage(it.restaurantsResponseData.restaurantsResponseData.currentPage)
                            repository.setTotalPages(it.restaurantsResponseData.restaurantsResponseData.totalPages)

                            fragment.setCategoryList(repository.getCategoryList())
                            fragment.resetRestaurantList(repository.getRestaurantList())
                        } else {
                            fragment.showPopup(it.message + "")
                        }
                    }
                } else -> {
                    fragment.showPopup(R.string.loading_restaurants_failed)
                }
            }
        }
    }

    fun getRestaurants(fragment: RestaurantsListFragmentView) {
        viewModelScope.launch {
            when (val response = repository.getRestaurants(fragment.getQuery(), getSelectedCategoriesIds())) {
                is Result.Success -> {
                    val getRestaurantResponse = response.value
                    getRestaurantResponse?.let {
                        if (it.isSuccessful) {
                            if (it.restaurantsResponseData.restaurants.size > 0) {
                                if (reset || fragment.reset()) {
                                    reset = false
                                    fragment.resetConsumed()
                                    repository.resetRestaurantList(it.restaurantsResponseData.restaurants)
                                    fragment.resetRestaurantList(it.restaurantsResponseData.restaurants)
                                } else {
                                    repository.setRestaurantList(it.restaurantsResponseData.restaurants)
                                    fragment.setRestaurantList(it.restaurantsResponseData.restaurants)
                                }

                                repository.setCurrentPage(it.restaurantsResponseData.currentPage)
                                repository.setTotalPages(it.restaurantsResponseData.totalPages)
                            }
                        } else {
                            fragment.showPopup(it.message + "")
                        }
                    }
                }
                else -> {
                    fragment.showPopup(R.string.loading_restaurants_failed)
                }
            }
        }
    }

    private fun getSelectedCategoriesIds(): ArrayList<Int> {
        val ids = arrayListOf<Int>()

        for (i in 0 until selectedCategories.size) {
            ids.add(selectedCategories[i].id)
        }

        return ids
    }

    fun onCategoryItemClick(fragment: RestaurantsListFragmentView): RecyclerViewItemClickListener<Category> = object: RecyclerViewItemClickListener<Category> {
        override fun onItemClick(item: Category) {
            reset = true
            if (item.isSelected) {
                item.isSelected = false
                selectedCategories.remove(item)
            } else {
                item.isSelected = true
                selectedCategories.add(item)
            }
            getRestaurants(fragment)
        }
    }

    fun onRestaurantItemClick(fragment: RestaurantsListFragmentView): RecyclerViewItemClickListener<Restaurant> = object: RecyclerViewItemClickListener<Restaurant> {
        override fun onItemClick(item: Restaurant) {
            repository.setSelectedRestaurant(item)
            fragment.goToRestaurantDetails()
        }
    }

    fun loadNextRestaurants(fragment: RestaurantsListFragmentView) {
        if (repository.getCurrentPage() + 1 < repository.getTotalPages())
            getRestaurants(fragment)
    }

    fun getPreviouslyLoadedRestaurants(): ArrayList<Restaurant> = repository.getRestaurantList()
    fun getPreviouslyLoadedCategories(): ArrayList<Category> = repository.getCategoryList()
}