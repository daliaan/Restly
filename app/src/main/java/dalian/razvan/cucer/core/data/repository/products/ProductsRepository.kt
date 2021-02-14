package dalian.razvan.cucer.core.data.repository.products

import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.network.model.response.products.InitProductsResponse
import dalian.razvan.cucer.models.product.Product
import dalian.razvan.cucer.models.restaurant.Category
import dalian.razvan.cucer.models.restaurant.Restaurant

interface ProductsRepository {

    suspend fun initRestaurantMenu(restaurantId: Int): Result<InitProductsResponse?>
    suspend fun getRestaurants(query: String, list: ArrayList<Int>)

    fun setProductList(list: ArrayList<Product>)
    fun resetProductList(list: ArrayList<Product>)

    fun setCategoryList(list: ArrayList<Category>)

    fun getProductList(): ArrayList<Product>
    fun getCategoryList(): ArrayList<Category>

    fun setCurrentPage(page: Int)
    fun setTotalPages(pages: Int)
    fun setSelectedProduct(product: Product?)
    fun getCurrentPage(): Int
    fun getTotalPages(): Int
}