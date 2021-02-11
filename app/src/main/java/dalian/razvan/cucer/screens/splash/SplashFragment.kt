package dalian.razvan.cucer.screens.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.Navigation
import dalian.razvan.cucer.R
import dalian.razvan.cucer.core.baseClasses.BaseFragment

class SplashFragment: BaseFragment() {

    override fun whichLayout(): Int = R.layout.fragment_splash

    override fun onResume() {
        super.onResume()
        Handler().postDelayed(Runnable{
            view?.let {
                Navigation.findNavController(it).popBackStack()
                Navigation.findNavController(it).navigate(R.id.go_to_restaurants)
            }
        }, 1500)
    }
}