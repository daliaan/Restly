package dalian.razvan.cucer.core.data.network.model.response.products

import com.google.gson.annotations.SerializedName

data class InitProductsResponse(@SerializedName("success") val isSuccessful: Boolean,
                                @SerializedName("statusCode") val statusCode: Int = 0,
                                @SerializedName("message") val message: String? = null,
                                @SerializedName("data") val initProductsResponseData: InitProductsResponseData)
