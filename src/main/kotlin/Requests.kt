import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import okhttp3.OkHttpClient
import okhttp3.Request

const val EMPTY_RESPONSE = "empty_response"
const val URL_FAIL = "http://dummy.restapiexample.com/"
const val URL_DUMMY_POSTS_JSON = "https://jsonplaceholder.typicode.com/posts/"

val okHttpClient: OkHttpClient = OkHttpClient()

fun get(url: String): Observable<String> {
    return Observable.fromCallable {
        val request = Request.Builder()
            .url(url)
            .build()
        println("Requesting $request")
        val response = okHttpClient.newCall(request).execute()
        if (response.isSuccessful)
            response.body?.string() ?: EMPTY_RESPONSE
        else
            throw Exception("Request w/ url: $url has failed w/ code: ${response.code} & body: ${response.body?.string()}")
    }
}

fun getPosts() = get(URL_DUMMY_POSTS_JSON)

fun getPostForId(id: String) = get("${URL_DUMMY_POSTS_JSON}${id}")

val oneTimeRequest: Observable<String> by lazy {
    ReplaySubject.create<String>().also { subject ->
        getPostForId(10.toString())
            .subscribeOn(Schedulers.io())
            .doOnNext { subject.onNext(it) }
            .subscribe()
    }
}

