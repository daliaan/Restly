package dalian.razvan.cucer.core.data.network

import dalian.razvan.cucer.core.data.network.model.request.RestaurantsRequest
import dalian.razvan.cucer.core.data.network.model.response.products.InitProductsResponse
import dalian.razvan.cucer.core.data.network.model.response.products.ProductDetailsResponse
import dalian.razvan.cucer.core.data.network.model.response.restaurants.InitRestaurantsResponse
import dalian.razvan.cucer.core.data.network.model.response.restaurants.RestaurantsResponse
import dalian.razvan.cucer.core.data.repository.Endpoints
import retrofit2.Response
import retrofit2.http.*

interface API {

    @POST(Endpoints.INIT_RESTAURANTS)
    suspend fun initiateRestaurants(): Response<InitRestaurantsResponse>
    @POST(Endpoints.GET_RESTAURANTS)
    suspend fun getRestaurants(@Body initRestaurants: RestaurantsRequest): Response<RestaurantsResponse>

    @GET(Endpoints.INIT_RESTAURANT_MENU)
    suspend fun initiateRestaurantMenu(@Query("restaurantId") restaurantId: Int): Response<InitProductsResponse>
    @GET(Endpoints.GET_PRODUCT_BY_ID)
    suspend fun getProductById(@Query("productId") productId: Int): Response<ProductDetailsResponse>

}