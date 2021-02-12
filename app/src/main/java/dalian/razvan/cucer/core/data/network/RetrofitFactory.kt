package dalian.razvan.cucer.core.data.network

import android.content.Context
import dalian.razvan.cucer.core.data.repository.Endpoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    fun create(context: Context): API {
        val okHttpClient = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.readTimeout(25, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(25, TimeUnit.SECONDS)

        return Retrofit.Builder()
                .baseUrl(Endpoints.BASE_URL)
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(API::class.java)
    }
}