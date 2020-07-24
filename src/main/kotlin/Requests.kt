import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.Request

const val EMPTY_RESPONSE = "empty_response"

val okHttpClient: OkHttpClient = OkHttpClient()

fun get(url: String): Observable<String> {
    return Observable.fromCallable {
        val request = Request.Builder()
            .url(url)
            .build()
        val response = okHttpClient.newCall(request).execute()
        if (response.isSuccessful)
            response.body?.string() ?: EMPTY_RESPONSE
        else
            throw Exception("Request w/ url: $url has failed w/ code: ${response.code} & body: ${response.body?.string()}")
    }
}