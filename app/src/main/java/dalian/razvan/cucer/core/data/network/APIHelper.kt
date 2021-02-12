package dalian.razvan.cucer.core.data.network

import android.util.Log
import dalian.razvan.cucer.core.data.event.GlobalServerErrorEvent
import org.greenrobot.eventbus.EventBus
import kotlinx.coroutines.CancellationException
import retrofit2.Response
import java.lang.Exception


private fun <T> parseResponse(response: Response<T>): Result<T?> {
    return if (response.isSuccessful) {
        Result.Success(response.body())
    } else {
        val errorMessage = response.errorBody()?.string()
        EventBus.getDefault().post(GlobalServerErrorEvent(errorMessage))
        Result.Error(
            code = response.code(),
            errorBody = errorMessage,
            errors = consumeError(errorMessage ?: "")
        )
    }
}

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Result<T?> {
    return try {
        parseResponse(apiCall.invoke())
    } catch (e: Exception) {
        Log.e("restly_", Log.getStackTraceString(e))
        when (e) {
            !is CancellationException -> EventBus.getDefault()
                .post(GlobalServerErrorEvent(e.message))
        }
        Result.Error(errorBody = e.message)
    }
}