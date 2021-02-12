package dalian.razvan.cucer.dependencyInjection

import dalian.razvan.cucer.core.data.network.RetrofitFactory
import dalian.razvan.cucer.core.data.repository.cart.CartRepository
import dalian.razvan.cucer.core.data.repository.cart.CartRepositoryImpl
import dalian.razvan.cucer.core.data.repository.products.ProductsRepository
import dalian.razvan.cucer.core.data.repository.products.ProductsRepositoryImpl
import dalian.razvan.cucer.core.data.repository.restaurants.RestaurantsRepository
import dalian.razvan.cucer.core.data.repository.restaurants.RestaurantsRepositoryImpl
import dalian.razvan.cucer.core.data.sharedPrefs.SharedPrefs
import dalian.razvan.cucer.core.data.sharedPrefs.SharedPrefsImpl
import dalian.razvan.cucer.screens.cart.CartViewModel
import dalian.razvan.cucer.screens.productsList.ProductsListViewModel
import dalian.razvan.cucer.screens.restaurantsList.RestaurantsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {

}

val repositoryModule = module {
    single { SharedPrefsImpl(androidContext()) as SharedPrefs }
    single { RestaurantsRepositoryImpl(get(), get()) as RestaurantsRepository }
    single { ProductsRepositoryImpl(get(), get()) as ProductsRepository}
    single { CartRepositoryImpl(get()) as CartRepository }
}

val viewModelModule = module {
    viewModel{ RestaurantsListViewModel(get()) }
    viewModel{ ProductsListViewModel(get()) }
    viewModel{ CartViewModel(get()) }
}

val networkModule = module {
    single { RetrofitFactory.create(get()) }
}

val serviceModule = module {

}