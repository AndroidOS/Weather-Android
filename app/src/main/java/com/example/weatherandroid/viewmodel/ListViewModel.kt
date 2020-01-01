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


    val stackItems = MutableLiveData<List<RootObject>>()
    val loading = MutableLiveData<Boolean>()

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
                    override fun onSuccess(itemList: RootObject) {


                        //soItems.value = itemList.items
                        if (itemList.main != null) {

                            //fetchFromDatabase()
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