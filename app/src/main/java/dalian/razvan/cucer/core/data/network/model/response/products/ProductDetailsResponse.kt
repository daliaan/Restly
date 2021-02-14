package dalian.razvan.cucer.core.data.network.model.response.products

import com.google.gson.annotations.SerializedName
import dalian.razvan.cucer.models.product.Product

data class ProductDetailsResponse(@SerializedName("data") val data: Product,
                                  @SerializedName("success") val isSuccessful: Boolean,
                                  @SerializedName("statusCode") val statusCode: Int = 0,
                                  @SerializedName("message") val message: String? = null)
