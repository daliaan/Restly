package dalian.razvan.cucer.core.baseClasses

import android.content.DialogInterface
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import dalian.razvan.cucer.R
import dalian.razvan.cucer.screens.RestlyActivity
import dalian.razvan.cucer.screens.cart.CartFragment
import dalian.razvan.cucer.screens.productDetails.ProductDetailsFragment
import dalian.razvan.cucer.screens.productsList.ProductsListFragment
import dalian.razvan.cucer.screens.restaurantsList.RestaurantsListFragment
import kotlinx.android.synthetic.main.fragment_base_restly.*
import kotlinx.android.synthetic.main.restly_search_view.*
import kotlinx.android.synthetic.main.restly_search_view.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*

abstract class BaseFragment: Fragment(), BaseFragmentView, BottomNavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemReselectedListener {

    protected var wasPaused = false
    protected lateinit var appActivity: RestlyActivity

    abstract fun whichLayout(): Int
    abstract fun toolbarTitle(): Int
    abstract fun toolbarHint(): Int
    abstract fun showToolbar(): Boolean
    abstract fun showBottombar(): Boolean

    abstract fun getTextWatcher(): TextWatcher?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let {
            appActivity = it as RestlyActivity
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
                = inflater.inflate(R.layout.fragment_base_restly, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment_container.addView(LayoutInflater.from(view.context).inflate(whichLayout(), null))
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        setToolbar()
        setBottombar()
    }

    override fun onPause() {
        super.onPause()
        wasPaused = true
    }

    override fun showProgressBar(show: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showPopup(text: String, onClickListener: DialogInterface.OnClickListener?, onDismissClickListener: DialogInterface.OnDismissListener?) {
        val alertDialogBuilder = AlertDialog.Builder(appActivity)
        alertDialogBuilder.setTitle("")
        alertDialogBuilder.setMessage(text)
        alertDialogBuilder.setPositiveButton(resources.getString(android.R.string.ok)) { dialog: DialogInterface?, which: Int ->
            onClickListener?.onClick(dialog, which)
            dialog?.dismiss()
        }
        alertDialogBuilder.setOnDismissListener { dialog: DialogInterface? ->
            onDismissClickListener?.onDismiss(dialog)
            dialog?.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setOnShowListener { dialog: DialogInterface? ->

        }
        alertDialog.show()
    }

    override fun showPopup(text: String) {
        showPopup(text, null, null)
    }

    override fun showPopup(resId: Int) {
        showPopup(resources.getString(resId))
    }

    override fun goBack() {
        view?.let {
            val navController = Navigation.findNavController(it)
            if (navController.previousBackStackEntry == null) {
                activity?.finish()
            } else {
                navController.popBackStack()
            }
        }
    }

    override fun isActivityStateSaved(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isActivityVisible(): Boolean {
        TODO("Not yet implemented")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_menu -> {
                if (this !is RestaurantsListFragment) {
                    view?.let {
                        Navigation.findNavController(it).popBackStack()
                        Navigation.findNavController(it).navigate(R.id.go_to_restaurants)
                    }
                }
            }
            R.id.item_order -> {
                if (this !is CartFragment) {
                    view?.let {
                        Navigation.findNavController(it).popBackStack()
                        Navigation.findNavController(it).navigate(R.id.go_to_cart)
                    }
                }
            }
            R.id.item_tab -> {

            }
            R.id.item_account -> {

            }
        }
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {

    }

    private fun setToolbar() {
        if (showToolbar()) {
            toolbar_title.text = getString(toolbarTitle())
            toolbar_search_view.setHint(getString(toolbarHint()))
            getTextWatcher()?.let {
                toolbar_search_view.search_edit_text.addTextChangedListener(it)
            }
            search_clear.setOnClickListener {
                toolbar_search_view.search_edit_text.setText("")
            }
        } else {
            toolbar_layout.visibility = View.GONE
        }
    }

    private fun setBottombar() {
        if (showBottombar()) {
            bottom_bar.setOnNavigationItemReselectedListener(this)
            bottom_bar.setOnNavigationItemSelectedListener(this)

            if (this is RestaurantsListFragment || this is ProductsListFragment || this is ProductDetailsFragment) {
                bottom_bar.selectedItemId = R.id.item_menu
            } else if (this is CartFragment) {
                bottom_bar.selectedItemId = R.id.item_order
            }
        } else {
            bottom_bar.visibility = View.GONE
        }
    }
}