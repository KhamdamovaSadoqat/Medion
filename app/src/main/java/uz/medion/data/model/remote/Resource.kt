package uz.medion.data.model.remote

import java.util.*

// A generic class that contains data and status about loading this data.
/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: Any?,
    val throwable: Throwable?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {

            return Resource(
                Status.SUCCESS,
                data,
                null,
                null
            )
        }

        fun <T> error(msg: String?, data: T?, throwable: Throwable?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg,
                throwable
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null,
                null
            )
        }
    }
}