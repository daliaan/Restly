package dalian.razvan.cucer.screens.productsList

import dalian.razvan.cucer.core.baseClasses.BaseFragmentView
import dalian.razvan.cucer.models.product.Product
import dalian.razvan.cucer.models.restaurant.Category
import java.util.ArrayList

interface ProductsListFragmentView: BaseFragmentView {

    fun setCategoryList(categoryList: ArrayList<Category>)

    fun setProductList(productList: ArrayList<Product>)
    fun resetProductList(productList: ArrayList<Product>)

    fun getQuery(): String
    fun reset(): Boolean
    fun resetConsumed()
    fun goToProductDetails()
}
