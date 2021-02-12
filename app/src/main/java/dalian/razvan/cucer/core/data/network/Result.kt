package dalian.razvan.cucer.core.data.network

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Error(
        val code: Int? = null,
        val errorBody: String? = null,
        val errors: List<ApiError> = listOf()
    ) : Result<Nothing>()
}