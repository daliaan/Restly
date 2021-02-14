package dalian.razvan.cucer.screens.productsList

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment
import dalian.razvan.cucer.customViews.recyclerViews.category.CategoryAdapter
import dalian.razvan.cucer.customViews.recyclerViews.product.ProductAdapter
import dalian.razvan.cucer.models.product.Product
import dalian.razvan.cucer.models.restaurant.Category
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.fragment_products.category_list
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.android.synthetic.main.restly_search_view.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

class ProductsListFragment: BaseFragment(), ProductsListFragmentView, TextWatcher {

    private val productsListViewModel by viewModel<ProductsListViewModel>()
    private var mustReset = false

    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var productsAdapter: ProductAdapter

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                productsListViewModel.loadNextProducts(this@ProductsListFragment)
            }
        }
    }

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

        product_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        product_list.setHasFixedSize(false)
        productsAdapter = ProductAdapter()
        productsAdapter.addItemClickListener(productsListViewModel.onProductItemClick(this))
        product_list.adapter = productsAdapter

        product_list.addOnScrollListener(onScrollListener)

        raise_hand.setOnClickListener {
            productsListViewModel.raisedHand(it.context)
        }
    }

    override fun onResume() {
        super.onResume()
        if (wasPaused) {
            wasPaused = false
            categoriesAdapter.resetList(productsListViewModel.getPreviouslyLoadedCategories())
            productsAdapter.resetList(productsListViewModel.getPreviouslyLoadedProducts())
        } else {
            productsListViewModel.loadProducts(this)
        }
    }

    override fun setCategoryList(categoryList: ArrayList<Category>) {
        if (categoryList.size == 0) {
            category_list.visibility = View.GONE
        } else {
            category_list.visibility = View.VISIBLE
            categoriesAdapter.resetList(categoryList)
        }
    }

    override fun setProductList(productList: ArrayList<Product>) {
        productsAdapter.setList(productList)
    }

    override fun resetProductList(productList: ArrayList<Product>) {
        productsAdapter.resetList(productList)
    }

    override fun getQuery(): String = toolbar_search_view.search_edit_text.text.toString()
    override fun reset(): Boolean = mustReset

    override fun resetConsumed() {
        mustReset = false
    }

    override fun goToProductDetails() {
        view?.let {
            Navigation.findNavController(it).navigate(R.id.go_to_product_details)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        mustReset = true
    }

    override fun afterTextChanged(s: Editable?) {
        s?.let {
            if (it.length > 2) {
                productsListViewModel.getProducts(this)
            }
        }
    }
}