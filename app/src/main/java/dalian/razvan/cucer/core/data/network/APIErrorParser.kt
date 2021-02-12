package dalian.razvan.cucer.core.data.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


fun consumeError(errorBody: String): List<ApiError> {
    if (errorBody.isEmpty()) listOf(
        ApiError(
            cause = "Empty error description"
        )
    )

    val objectsList: Type = object : TypeToken<JsonObject>() {}.type
    val gson = Gson()
    return try {
        val errorResponse: JsonObject = gson.fromJson(errorBody, objectsList)
        return parseError(errorResponse)
    } catch (ecp: Exception) {
        listOf(ApiError( cause = "Can`t parse Error"))
    }
}
private fun parseError(errorBody: JsonObject): List<ApiError> {
    val errorMap = mutableMapOf<String, String>()
    val gson = Gson()

    for ((field, cause) in errorBody.entrySet()) {
        try {
            val strCause = gson.fromJson(cause, JsonArray::class.java)
                .first()
                .toString()
                .replace("\"", "")

            errorMap[field] = strCause
        } catch (excp: Exception) {
            Log.d("restly_ApiError", "Can`t parse json, cause\t${excp.localizedMessage}")
        }
    }
    return parseMapToErrors(errorMap)
}


private fun parseMapToErrors(errorsInMap: MutableMap<String, String>): List<ApiError> {
    return errorsInMap.map { entry ->
        ApiError(
            originField = entry.key,
            cause = entry.value,
        )
    }.toList()
}

data class ApiError(
    val originField: String? = null,
    val cause: String? = null,
) {
    override fun toString(): String {
        return "originField:$originField, cause:$cause"
    }
}