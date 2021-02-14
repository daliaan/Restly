package dalian.razvan.cucer.screens.cart

import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment

class CartFragment: BaseFragment() {
    override fun whichLayout(): Int = R.layout.fragment_cart
    override fun toolbarTitle(): Int = R.string.empty_string
    override fun toolbarHint(): Int = R.string.empty_string
    override fun showToolbar(): Boolean = true
    override fun showBottombar(): Boolean = true
}