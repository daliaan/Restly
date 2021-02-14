package dalian.razvan.cucer.core.data.repository.restaurants

import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.network.model.request.RestaurantsRequest
import dalian.razvan.cucer.core.data.network.model.response.InitRestaurantsResponse
import dalian.razvan.cucer.core.data.network.model.response.RestaurantsResponse
import dalian.razvan.cucer.models.restaurant.Category
import dalian.razvan.cucer.models.restaurant.Restaurant

interface RestaurantsRepository {

    suspend fun initRestaurants(): Result<InitRestaurantsResponse?>
    suspend fun getRestaurants(query: String, list: ArrayList<Int>): Result<RestaurantsResponse?>

    fun setRestaurantList(list: ArrayList<Restaurant>)
    fun resetRestaurantList(list: ArrayList<Restaurant>)

    fun setCategoryList(list: ArrayList<Category>)

    fun getRestaurantList(): ArrayList<Restaurant>
    fun getCategoryList(): ArrayList<Category>

    fun setCurrentPage(page: Int)
    fun setTotalPages(pages: Int)
    fun setSelectedRestaurant(restaurant: Restaurant?)
    fun getCurrentPage(): Int
    fun getTotalPages(): Int
}