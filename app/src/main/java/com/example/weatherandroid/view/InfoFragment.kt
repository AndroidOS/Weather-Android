package com.example.weatherandroid.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.weatherandroid.R
import com.example.weatherandroid.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_info.*


class InfoFragment : Fragment() {

    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[ListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        (viewModel as ListViewModel).refresh()

        observeViewModel()

    }

    fun observeViewModel() {
        (viewModel as ListViewModel).weather.observe(this, Observer { weather ->
            weather?.let {
                txt_temperature.text = "Temperature: " + weather.main?.temp_max.toString()
                txt_conditions.text = weather.weather?.get(0)?.description.toString()
            }
        })

        (viewModel as ListViewModel).city.observe(this, Observer {
            Toast.makeText(
                activity,
                "${(viewModel as ListViewModel).city.value}",
                Toast.LENGTH_SHORT
            ).show()

        })

    }


}
