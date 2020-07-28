import com.google.gson.Gson
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.Executors

val scheduler: Scheduler = Schedulers.from(Executors.newSingleThreadExecutor())

fun main() {

    range4Timer()
        .doOnNext(::println)
        .subscribe()

    while (readLine() != 1.toString())
        break
}