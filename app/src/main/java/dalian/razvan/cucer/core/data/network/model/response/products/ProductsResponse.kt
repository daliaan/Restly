package dalian.razvan.cucer.core.data.network.model.response.products

import com.google.gson.annotations.SerializedName
import dalian.razvan.cucer.core.data.network.model.response.restaurants.RestaurantsResponseData

data class ProductsResponse(@SerializedName("success") val isSuccessful: Boolean,
                            @SerializedName("statusCode") val statusCode: Int = 0,
                            @SerializedName("message") val message: String? = null,
                            @SerializedName("data") val productsResponseData: ProductsResponseData)
