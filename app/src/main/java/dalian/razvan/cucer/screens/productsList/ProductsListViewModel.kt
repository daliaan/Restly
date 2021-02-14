package dalian.razvan.cucer.screens.productsList

import androidx.lifecycle.viewModelScope
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseViewModel
import dalian.razvan.cucer.core.baseClasses.RecyclerViewItemClickListener
import dalian.razvan.cucer.core.data.network.Result
import dalian.razvan.cucer.core.data.repository.products.ProductsRepository
import dalian.razvan.cucer.core.data.repository.restaurants.RestaurantsRepository
import dalian.razvan.cucer.models.product.Product
import dalian.razvan.cucer.models.restaurant.Category
import dalian.razvan.cucer.models.restaurant.Restaurant
import kotlinx.coroutines.launch

class ProductsListViewModel(private val repository: ProductsRepository, private val restaurantsRepository: RestaurantsRepository): BaseViewModel() {

    private var reset = true
    private val selectedCategories = arrayListOf<Category>()

    fun loadProducts(fragment: ProductsListFragmentView) {
        viewModelScope.launch {
            if (repository.getProductList().size > 0) {

            } else {
                initRestaurantMenu(fragment)
            }
        }
    }

    private fun initRestaurantMenu(fragment: ProductsListFragmentView) {
        viewModelScope.launch {
            when(val response = repository.initRestaurantMenu()) {
                is Result.Success -> {
                    val initRestaurantMenuResponse = response.value
                    initRestaurantMenuResponse?.let {
                        if (it.isSuccessful) {
                            repository.setCategoryList(it.initProductsResponseData.categories)
                            repository.resetProductList(it.initProductsResponseData.productsResponseData.products)

                            repository.setCurrentPage(it.initProductsResponseData.productsResponseData.currentPage)
                            repository.setTotalPages(it.initProductsResponseData.productsResponseData.totalPages)

                            fragment.setCategoryList(repository.getCategoryList())
                            fragment.resetProductList(repository.getProductList())
                        } else {
                            fragment.showPopup(it.message + "")
                        }
                    }
                } else -> {
                    fragment.showPopup(R.string.loading_products_failed)
                }
            }
        }
    }

    fun onCategoryItemClick(fragment: ProductsListFragmentView): RecyclerViewItemClickListener<Category>  = object: RecyclerViewItemClickListener<Category> {
        override fun onItemClick(item: Category) {
            reset = true
            if (item.isSelected) {
                item.isSelected = false
                selectedCategories.remove(item)
            } else {
                item.isSelected = true
                selectedCategories.add(item)
            }
            getProducts(fragment)
        }
    }

    fun onProductItemClick(fragment: ProductsListFragmentView): RecyclerViewItemClickListener<Product>  = object: RecyclerViewItemClickListener<Product> {
        override fun onItemClick(item: Product) {
            repository.setSelectedProduct(item)
            fragment.goToProductDetails()
        }
    }

    private fun getProducts(fragment: ProductsListFragmentView) {

    }

    private fun loadNextProducts(fragment: ProductsListFragmentView) {
        if (repository.getCurrentPage() + 1 < repository.getTotalPages())
            getProducts(fragment)
    }
}