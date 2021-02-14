package dalian.razvan.cucer.core.data.repository.products

import dalian.razvan.cucer.core.data.network.API
import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.network.model.request.ProductsRequest
import dalian.razvan.cucer.core.data.network.model.response.products.InitProductsResponse
import dalian.razvan.cucer.core.data.network.safeApiCall
import dalian.razvan.cucer.core.data.sharedPrefs.SharedPrefs
import dalian.razvan.cucer.models.product.Product
import dalian.razvan.cucer.models.restaurant.Category
import dalian.razvan.cucer.models.restaurant.Restaurant

class ProductsRepositoryImpl(private val api: API, private val sharedPrefs: SharedPrefs): ProductsRepository {

    private val products = arrayListOf<Product>()
    private val categories = arrayListOf<Category>()

    private var currentPage = 0
    override fun getCurrentPage(): Int = currentPage

    private var totalPages = 0
    override fun getTotalPages(): Int = totalPages

    private val pageSize = 10

    private var selectedProduct: Product? = null

    override suspend fun initRestaurantMenu(restaurantId: Int): Result<InitProductsResponse?>  = safeApiCall { api.initiateRestaurantMenu(restaurantId) }
    override suspend fun getProducts(query: String, list: ArrayList<Int>, restaurantId: Int) = safeApiCall { api.getProducts(getProductsParams(query, list, restaurantId)) }

    private fun getProductsParams(query: String, list: java.util.ArrayList<Int>, restaurantId: Int): ProductsRequest
            = ProductsRequest(generatePageNumber(query, list), pageSize = pageSize, restaurantId = restaurantId,categoryIds = list, query = query)

    override fun setProductList(list: ArrayList<Product>) {
        products.addAll(list)
    }

    override fun resetProductList(list: ArrayList<Product>) {
        products.clear()
        setProductList(list)
    }

    override fun setCategoryList(list: ArrayList<Category>) {
        categories.addAll(list)
    }

    override fun getProductList(): ArrayList<Product> = products
    override fun getCategoryList(): ArrayList<Category> = categories

    override fun setCurrentPage(page: Int) {
        currentPage = page
    }

    override fun setTotalPages(pages: Int) {
        totalPages = pages
    }

    override fun setSelectedProduct(product: Product?) {
        selectedProduct = product
    }

    private fun generatePageNumber(query: String, list: java.util.ArrayList<Int>): Int {
        if (!(query.isBlank() || query.isEmpty()) || list.size > 0)
            return 0
        return currentPage + 1
    }
}