package dalian.razvan.cucer.screens.productDetails

import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment

class ProductDetailsFragment: BaseFragment() {
    override fun whichLayout(): Int = R.layout.fragment_product_details
    override fun toolbarTitle(): Int = R.string.empty_string
}