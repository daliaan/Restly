package dalian.razvan.cucer.screens.restaurantsList

import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantsListFragment: BaseFragment(), RestaurantsListFragmentView {

    private val restaurantsViewModel by viewModel<RestaurantsListViewModel>()

    override fun whichLayout(): Int = R.layout.fragment_restaurants
    override fun toolbarTitle(): Int = R.string.restaurants_title
    override fun toolbarHint(): Int = R.string.restaurant_or_product
    override fun showToolbar(): Boolean = true

    override fun onResume() {
        super.onResume()
        restaurantsViewModel.loadRestaurants(this)
    }
}