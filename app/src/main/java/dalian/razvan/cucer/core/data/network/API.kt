package dalian.razvan.cucer.core.data.network

import dalian.razvan.cucer.core.data.network.model.request.InitRestaurantsRequest
import dalian.razvan.cucer.core.data.network.model.request.RestaurantsRequest
import dalian.razvan.cucer.core.data.network.model.response.InitRestaurantsResponse
import dalian.razvan.cucer.core.data.network.model.response.RestaurantsResponse
import dalian.razvan.cucer.core.data.repository.Endpoints
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface API {

    @POST(Endpoints.INIT_RESTAURANTS)
    suspend fun initiateRestaurants(@Body initRestaurants: InitRestaurantsRequest): Response<InitRestaurantsResponse>
    @POST(Endpoints.GET_RESTAURANTS)
    suspend fun getRestaurants(@Body initRestaurants: RestaurantsRequest): Response<RestaurantsResponse>
}