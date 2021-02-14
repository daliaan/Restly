package dalian.razvan.cucer.screens.splash

import android.os.Bundle
import android.os.Handler
import android.text.TextWatcher
import android.view.View
import androidx.navigation.Navigation
import coil.Coil
import coil.load
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment: BaseFragment() {

    override fun whichLayout(): Int = R.layout.fragment_splash
    override fun toolbarTitle(): Int = R.string.empty_string
    override fun toolbarHint(): Int = R.string.empty_string
    override fun showToolbar(): Boolean = false
    override fun showBottombar(): Boolean = false
    override fun getTextWatcher(): TextWatcher? = null

    override fun onResume() {
        super.onResume()
        splash_screen_image.load(R.drawable.splash)
        Handler().postDelayed(Runnable{
            view?.let {
                Navigation.findNavController(it).popBackStack()
                Navigation.findNavController(it).navigate(R.id.go_to_restaurants)
            }
        }, 1500)
    }
}