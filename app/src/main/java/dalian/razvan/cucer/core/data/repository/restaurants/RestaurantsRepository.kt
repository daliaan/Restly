package dalian.razvan.cucer.core.data.repository.restaurants

import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.network.model.response.InitRestaurantsResponse

interface RestaurantsRepository {

    suspend fun initRestaurants(): Result<InitRestaurantsResponse?>
}