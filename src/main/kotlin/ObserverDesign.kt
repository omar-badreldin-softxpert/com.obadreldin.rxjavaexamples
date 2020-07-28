open class Observable<T> {
    var data: T? = null
        set(value) {
            observers.forEach {
                it.oneNewValue(value)
            }
        }

    private val observers: MutableList<Observer<T>> = mutableListOf()

    fun addObserver(observer: Observer<T>) = observers.add(observer)

    fun removeObserver(observer: Observer<T>) = observers.remove(observer)
}


abstract class Observer<T> {
    abstract fun oneNewValue(value: T?)
}


fun main() {
    val observable = Observable<Int>()

    val observer = object : Observer<Int>() {
        override fun oneNewValue(value: Int?) {
            println("$value")
        }
    }

    observable.addObserver(observer)
    observable.data = 2

    observable.removeObserver(observer)
}