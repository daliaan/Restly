package dalian.razvan.cucer.core.data.repository.restaurants

import dalian.razvan.cucer.core.data.network.API
import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.network.model.response.InitRestaurantsResponse
import dalian.razvan.cucer.core.data.network.safeApiCall
import dalian.razvan.cucer.core.data.sharedPrefs.SharedPrefs

class RestaurantsRepositoryImpl(var api: API, var sharedPrefs: SharedPrefs): RestaurantsRepository {
    override suspend fun initRestaurants(): Result<InitRestaurantsResponse?> = safeApiCall { api.initiateRestaurants() }
}