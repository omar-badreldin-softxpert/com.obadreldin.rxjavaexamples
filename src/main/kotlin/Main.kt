import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {

    get("https://jsonplaceholder.typicode.com/posts/2")
        .observeOn(Schedulers.io())
        .doOnNext { println(it) }
        .subscribe(Consumer {  }, Consumer {  })

    while (readLine() != "close")
        break
}