package dalian.razvan.cucer.screens.productsList

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment
import dalian.razvan.cucer.customViews.recyclerViews.category.CategoryAdapter
import dalian.razvan.cucer.customViews.recyclerViews.product.ProductAdapter
import dalian.razvan.cucer.models.product.Product
import dalian.razvan.cucer.models.restaurant.Category
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products.category_list
import kotlinx.android.synthetic.main.fragment_restaurants.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

class ProductsListFragment: BaseFragment(), ProductsListFragmentView {

    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var productsAdapter: ProductAdapter
    private val productsListViewModel by viewModel<ProductsListViewModel>()

    override fun whichLayout(): Int = R.layout.fragment_products
    override fun toolbarTitle(): Int = R.string.menu
    override fun toolbarHint(): Int = R.string.restaurant_or_product
    override fun showToolbar(): Boolean = true
    override fun showBottombar(): Boolean = true
    override fun getTextWatcher(): TextWatcher? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        category_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        category_list.setHasFixedSize(true)
        categoriesAdapter = CategoryAdapter()
        categoriesAdapter.addItemClickListener(productsListViewModel.onCategoryItemClick(this))
        category_list.adapter = categoriesAdapter
    }

    override fun onResume() {
        super.onResume()
        productsListViewModel.loadProducts(this)
    }

    override fun setCategoryList(categoryList: ArrayList<Category>) {
        categoriesAdapter.resetList(categoryList)
    }

    override fun setProductList(productList: ArrayList<Product>) {
//        TODO("Not yet implemented")
    }

    override fun resetProductList(productList: ArrayList<Product>) {
//        TODO("Not yet implemented")
    }

    override fun goToProductDetails() {
        view?.let {
            Navigation.findNavController(it).navigate(R.id.go_to_product_details)
        }
    }
}