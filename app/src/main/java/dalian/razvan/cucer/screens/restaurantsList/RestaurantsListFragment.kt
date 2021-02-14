package dalian.razvan.cucer.screens.restaurantsList

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
import dalian.razvan.cucer.customViews.recyclerViews.restaurant.RestaurantAdapter
import dalian.razvan.cucer.models.restaurant.Category
import dalian.razvan.cucer.models.restaurant.Restaurant
import kotlinx.android.synthetic.main.fragment_restaurants.*
import kotlinx.android.synthetic.main.restly_search_view.*
import kotlinx.android.synthetic.main.restly_search_view.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantsListFragment: BaseFragment(), RestaurantsListFragmentView, TextWatcher {

    private val restaurantsViewModel by viewModel<RestaurantsListViewModel>()
    private var mustReset = false

    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var restaurantsAdapter: RestaurantAdapter

    private val onScrollListener = object: RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                restaurantsViewModel.loadNextRestaurants(this@RestaurantsListFragment)
            }
        }
    }

    override fun whichLayout(): Int = R.layout.fragment_restaurants
    override fun toolbarTitle(): Int = R.string.restaurants_title
    override fun toolbarHint(): Int = R.string.restaurant_or_product
    override fun showToolbar(): Boolean = true
    override fun showBottombar(): Boolean = true
    override fun getTextWatcher(): TextWatcher = this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        category_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        category_list.setHasFixedSize(true)
        categoriesAdapter = CategoryAdapter()
        categoriesAdapter.addItemClickListener(restaurantsViewModel.onCategoryItemClick(this))
        category_list.adapter = categoriesAdapter

        restaurant_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        restaurant_list.setHasFixedSize(true)
        restaurantsAdapter = RestaurantAdapter()
        restaurantsAdapter.addItemClickListener(restaurantsViewModel.onRestaurantItemClick(this))
        restaurant_list.adapter = restaurantsAdapter

        restaurant_list.addOnScrollListener(onScrollListener)
    }

    override fun onResume() {
        super.onResume()
        if (wasPaused) {
            wasPaused = false
            categoriesAdapter.resetList(restaurantsViewModel.getPreviouslyLoadedCategories())
            restaurantsAdapter.resetList(restaurantsViewModel.getPreviouslyLoadedRestaurants())
        } else {
            restaurantsViewModel.loadRestaurants(this)
        }
    }

    override fun setCategoryList(list: ArrayList<Category>) {
        categoriesAdapter.setList(list)
    }

    override fun setRestaurantList(list: ArrayList<Restaurant>) {
        restaurantsAdapter.setList(list)
    }

    override fun resetRestaurantList(restaurantList: ArrayList<Restaurant>) {
        restaurantsAdapter.resetList(restaurantList)
    }

    override fun getQuery(): String = toolbar_search_view.search_edit_text.text.toString()
    override fun reset(): Boolean = mustReset

    override fun resetConsumed() {
        mustReset = false
    }

    override fun goToRestaurantDetails() {
        view?.let{
            Navigation.findNavController(it).navigate(R.id.go_to_products)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        mustReset = true
    }

    override fun afterTextChanged(s: Editable?) {
        s?.let {
            if (it.length > 2) {
                restaurantsViewModel.getRestaurants(this)
            }
        }
    }
}