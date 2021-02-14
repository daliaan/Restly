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
import java.util.ArrayList

class ProductsListViewModel(private val repository: ProductsRepository, private val restaurantsRepository: RestaurantsRepository): BaseViewModel() {

    private var reset = true
    private val selectedCategories = arrayListOf<Category>()

    fun loadProducts(fragment: ProductsListFragmentView) {
        viewModelScope.launch {
            if (repository.getProductList().size > 0) {
                getProducts(fragment)
            } else {
                initRestaurantMenu(fragment)
            }
        }
    }

    private fun initRestaurantMenu(fragment: ProductsListFragmentView) {
        viewModelScope.launch {
            restaurantsRepository.getSelectedRestaurant()?.let {
                when(val response = repository.initRestaurantMenu(it.id)) {
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
    }

    fun getProducts(fragment: ProductsListFragmentView) {
        viewModelScope.launch {
            restaurantsRepository.getSelectedRestaurant()?.let {
                when(val response = repository.getProducts(fragment.getQuery(), getSelectedCategoriesIds(), it.id)) {
                    is Result.Success -> {
                        val getProductsResponse = response.value
                        getProductsResponse?.let { productsResponse ->
                            if (productsResponse.isSuccessful) {
                                if (productsResponse.productsResponseData.products.size > 0) {
                                    if (reset || fragment.reset()) {
                                        reset = false
                                        fragment.resetConsumed()
                                        repository.resetProductList(productsResponse.productsResponseData.products)
                                        fragment.resetProductList(productsResponse.productsResponseData.products)
                                    } else {
                                        repository.setProductList(productsResponse.productsResponseData.products)
                                        fragment.setProductList(productsResponse.productsResponseData.products)
                                    }

                                    repository.setCurrentPage(productsResponse.productsResponseData.currentPage)
                                    repository.setTotalPages(productsResponse.productsResponseData.totalPages)
                                }
                            } else {
                                fragment.showPopup(productsResponse.message + "")
                            }
                        }
                    }
                    else -> {
                        fragment.showPopup(R.string.loading_products_failed)
                    }
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

    private fun getSelectedCategoriesIds(): ArrayList<Int> {
        val ids = arrayListOf<Int>()

        for (i in 0 until selectedCategories.size) {
            ids.add(selectedCategories[i].id)
        }

        return ids
    }

    fun loadNextProducts(fragment: ProductsListFragmentView) {
        if (repository.getCurrentPage() + 1 < repository.getTotalPages())
            getProducts(fragment)
    }

    fun getPreviouslyLoadedCategories(): ArrayList<Category> = repository.getCategoryList()
    fun getPreviouslyLoadedProducts(): ArrayList<Product> = repository.getProductList()

    fun raisedHand() {

    }
}