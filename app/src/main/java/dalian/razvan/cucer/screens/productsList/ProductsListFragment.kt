package dalian.razvan.cucer.screens.productsList

import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment

class ProductsListFragment: BaseFragment() {
    override fun whichLayout(): Int = R.layout.fragment_products
    override fun toolbarTitle(): Int = R.string.menu
    override fun toolbarHint(): Int = R.string.restaurant_or_product
    override fun showToolbar(): Boolean = true
}