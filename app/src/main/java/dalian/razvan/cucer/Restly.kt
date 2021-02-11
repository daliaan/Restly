package dalian.razvan.cucer

import android.app.Application
import dalian.razvan.cucer.dependencyInjection.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Restly: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Restly)
            modules(listOf(repositoryModule,
                            useCaseModule,
                            viewModelModule,
                            networkModule,
                            serviceModule))
        }
    }
}