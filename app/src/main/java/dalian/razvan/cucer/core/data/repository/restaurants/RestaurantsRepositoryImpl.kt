package dalian.razvan.cucer.core.data.repository.restaurants

import dalian.razvan.cucer.core.data.network.API
import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.network.model.request.RestaurantsRequest
import dalian.razvan.cucer.core.data.network.model.response.InitRestaurantsResponse
import dalian.razvan.cucer.core.data.network.model.response.RestaurantsResponse
import dalian.razvan.cucer.core.data.network.safeApiCall
import dalian.razvan.cucer.core.data.sharedPrefs.SharedPrefs
import dalian.razvan.cucer.models.restaurant.Category
import dalian.razvan.cucer.models.restaurant.Restaurant

class RestaurantsRepositoryImpl(var api: API, var sharedPrefs: SharedPrefs): RestaurantsRepository {

    private val restaurants = arrayListOf<Restaurant>()
    private val categories = arrayListOf<Category>()

    private var currentPage = 0
    private var totalPages = 0
    private val pageSize = 10

    override suspend fun initRestaurants(): Result<InitRestaurantsResponse?> = safeApiCall { api.initiateRestaurants() }
    override suspend fun getRestaurants(query: String, list: ArrayList<Int>): Result<RestaurantsResponse?> = safeApiCall { api.getRestaurants(getRestaurantsParams(query, list)) }

    private fun getRestaurantsParams(query: String, list: ArrayList<Int>): RestaurantsRequest
            = RestaurantsRequest(pageNumber = currentPage + 1, pageSize = pageSize, categoryIds = list, queryTerm = query)

    override fun setRestaurantList(list: ArrayList<Restaurant>) {
        restaurants.addAll(list)
    }

    override fun resetRestaurantList(list: ArrayList<Restaurant>) {
        restaurants.clear()
        setRestaurantList(list)
    }

    override fun setCategoryList(list: ArrayList<Category>) {
        categories.addAll(list)
    }

    override fun getRestaurantList(): ArrayList<Restaurant> = restaurants
    override fun getCategoryList(): ArrayList<Category> = categories

    override fun setCurrentPage(page: Int) {
        currentPage = page
    }

    override fun setTotalPages(pages: Int) {
        this.totalPages = pages
    }
}