package com.example.todoplanner

class TempObservable<T>(private var data: T? = null) {
    val observers = ArrayList<TempObserver<T>>()
    fun addObserver(observer: TempObserver<T>) {
        observers.add(observer)
    }

    fun updateValue(newValue: T) {
        data = newValue
        for (o in observers) {
            o.onValueChanged(data!!)
        }
    }

    interface TempObserver<T> {
        fun onValueChanged(newValue: T)
    }
}