package uz.medion.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Looper
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (!isNetworkAvailable()) {
            val handler = android.os.Handler(Looper.getMainLooper())
            handler.post {
//
//               this.snackbar=this.
                Toast.makeText(context, "Internet ulanmagan", Toast.LENGTH_SHORT).show()
            }
        }
        return chain.proceed(request)
//        this.snackbar.
//        this.snackBar.open('Your message here', 'Dismiss', {
//                duration: 2000
//        });
    }


    fun isNetworkAvailable(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo

        return networkInfo?.isConnectedOrConnecting ?: false
    }

}

