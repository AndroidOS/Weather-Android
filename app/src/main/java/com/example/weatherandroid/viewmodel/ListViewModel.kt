package com.example.weatherandroid.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.weatherandroid.model.RootObject
import com.example.weatherandroid.model.WeatherApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

private const val TAG = "ListViewModel"

class ListViewModel(application: Application) : BaseViewModel(application) {


    private val weatherService = WeatherApiService()
    private val disposable = CompositeDisposable()


    val weather = MutableLiveData<RootObject>()
    val loading = MutableLiveData<Boolean>()
    val city = MutableLiveData<String>()

    fun refresh() {
        fetchFromRemote()
    }


    private fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            weatherService.getWeather()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RootObject>() {
                    override fun onSuccess(item: RootObject) {


                        //soItems.value = itemList.items
                        if (item.main != null) {
                            weather.value = item
                            Log.d(TAG, "$item")
                        } else {
                            Toast.makeText(
                                getApplication(),
                                "Error retrieving items",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        //fetchFromDatabase()

                    }

                    override fun onError(e: Throwable) {
                        Log.d(TAG, " RxJava error")
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )


    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}