package dalian.razvan.cucer.screens.restaurantsList

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment
import dalian.razvan.cucer.customViews.recyclerViews.CategoryAdapter
import dalian.razvan.cucer.customViews.recyclerViews.RestaurantAdapter
import kotlinx.android.synthetic.main.fragment_restaurants.*
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantsListFragment: BaseFragment(), RestaurantsListFragmentView {

    private val restaurantsViewModel by viewModel<RestaurantsListViewModel>()

    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var restaurantsAdapter: RestaurantAdapter

    override fun whichLayout(): Int = R.layout.fragment_restaurants
    override fun toolbarTitle(): Int = R.string.restaurants_title
    override fun toolbarHint(): Int = R.string.restaurant_or_product
    override fun showToolbar(): Boolean = true
    override fun showBottombar(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        category_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        category_list.setHasFixedSize(true)
        categoriesAdapter = CategoryAdapter()
        category_list.adapter = categoriesAdapter

        restaurant_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        restaurant_list.setHasFixedSize(true)
        restaurantsAdapter = RestaurantAdapter()
        restaurant_list.adapter = restaurantsAdapter
    }

    override fun onResume() {
        super.onResume()
        restaurantsViewModel.loadRestaurants(this)
    }
}