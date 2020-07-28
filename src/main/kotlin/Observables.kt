import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun range1() = Observable.intervalRange(
    1,
    10,
    0,
    1,
    TimeUnit.SECONDS
)

fun range2() = Observable.fromIterable(1..10)


fun range3() = Observable.interval(1, TimeUnit.SECONDS)

fun range4Timer() = Observable.timer(3, TimeUnit.SECONDS)