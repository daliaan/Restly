package dalian.razvan.cucer.core.data.network.model.response.products

import com.google.gson.annotations.SerializedName
import dalian.razvan.cucer.models.restaurant.Category

data class InitProductsResponseData(@SerializedName("menuItemsFirstPage") val productsResponseData: ProductsResponseData,
                                    @SerializedName("menuCategories") val categories: ArrayList<Category> = arrayListOf())
