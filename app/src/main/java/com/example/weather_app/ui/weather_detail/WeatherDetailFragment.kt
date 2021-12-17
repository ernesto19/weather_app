package com.example.weather_app.ui.weather_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentWeatherDetailBinding
import com.example.weather_app.ui.adapters.ForecastAdapter
import com.example.weather_app.ui.adapters.ForecastHourlyAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class WeatherDetailFragment : Fragment() {

    private val weatherDetailViewModel: WeatherDetailViewModel by viewModel()
    private lateinit var binding: FragmentWeatherDetailBinding
    private val args: WeatherDetailFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_detail, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = weatherDetailViewModel

        loadData()
        initObservers()

        return binding.root
    }

    private fun initObservers() {
        weatherDetailViewModel.weatherForecastListLiveData.observe(viewLifecycleOwner, {
            it?.let {
                binding.forecastRecyclerView.apply {
                    adapter = ForecastAdapter(it)
                }
            }
            weatherDetailViewModel.weatherForecastListLiveData.postValue(null)
        })

        weatherDetailViewModel.weatherForecastHourlyListLiveData.observe(viewLifecycleOwner, {
            it?.let {
                binding.forecastHourlyRecyclerView.apply {
                    adapter = ForecastHourlyAdapter(it)
                }
            }
            weatherDetailViewModel.weatherForecastHourlyListLiveData.postValue(null)
        })

        weatherDetailViewModel.weatherDataLiveData.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvCity.text = it.name
                binding.tvTemperature.text = "${it.main?.temperature}°"
                binding.tvMaxAndMinTemperature.text = getString(
                    R.string.str_weather_max_and_min_temperature,
                    it.main?.maximumTemperature.toString(),
                    it.main?.minimumTemperature.toString()
                )
                binding.tvDescription.text = getString(
                    R.string.str_weather_description,
                    it.weather?.description ?: ""
                )
                binding.tvDate.text = getCurrentDate()
                setBackground(it.main?.temperature ?: 30)
            }
        })
    }

    private fun loadData() {
        weatherDetailViewModel.getWeatherByCityId(args.id ?: "")
        weatherDetailViewModel.getWeatherDetail(
            args.latitude?.toDouble() ?: 0.0,
            args.longitude?.toDouble() ?: 0.0
        )
    }

    private fun setBackground(temperature: Int) {
        binding.container.background = ContextCompat.getDrawable(
            requireContext(),
            when {
                temperature < 12 -> R.drawable.rainy_weather_background
                temperature in 12..15 -> R.drawable.cloudy_weather_background
                temperature in 16..22 -> R.drawable.cold_weather_background
                else -> R.drawable.hot_weather_background
            }
        )
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, d MMM", Locale("es", "ES"))
        return dateFormat.format(calendar.time).capitalize()
    }
}