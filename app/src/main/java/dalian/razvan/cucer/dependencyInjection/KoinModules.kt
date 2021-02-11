package dalian.razvan.cucer.dependencyInjection

import dalian.razvan.cucer.screens.cart.CartViewModel
import dalian.razvan.cucer.screens.productsList.ProductsListViewModel
import dalian.razvan.cucer.screens.restaurantsList.RestaurantsListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {

}

val repositoryModule = module {

}

val viewModelModule = module {
    viewModel{ RestaurantsListViewModel() }
    viewModel{ ProductsListViewModel() }
    viewModel{ CartViewModel() }
}

val networkModule = module {

}

val serviceModule = module {

}