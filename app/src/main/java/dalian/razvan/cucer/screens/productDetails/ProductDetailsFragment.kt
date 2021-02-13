package dalian.razvan.cucer.screens.productDetails

import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment
import dalian.razvan.cucer.screens.restaurantsList.RestaurantsListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProductDetailsFragment: BaseFragment() {

    private val productDetailsViewModel by viewModel<ProductDetailsViewModel>()

    override fun whichLayout(): Int = R.layout.fragment_product_details
    override fun toolbarTitle(): Int = R.string.empty_string
}