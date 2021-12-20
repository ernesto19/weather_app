package com.example.weather_app.ui.weather_consult

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.weather_app.R
import com.example.weather_app.databinding.FragmentWeatherConsultBinding
import com.example.weather_app.domain.entities.WeatherData
import com.example.weather_app.ui.adapters.FavoriteCitiesAdapter
import com.example.weather_app.utils.Constants.AUTOCOMPLETE_REQUEST_CODE
import com.example.weather_app.utils.Constants.GOOGLE_MAPS_API_KEY
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherConsultFragment : Fragment() {
    private val weatherConsultViewModel: WeatherConsultViewModel by viewModel()
    private lateinit var binding: FragmentWeatherConsultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_consult, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = weatherConsultViewModel

        initConfiguration()
        loadData()
        initObservers()
        initComponents()

        return binding.root
    }

    private fun initComponents() {
        binding.tvSearchCity.setOnClickListener {
            onSearchCalled()
        }
    }

    private fun initObservers() {
        weatherConsultViewModel.selectedWeatherLiveData.observe(viewLifecycleOwner, {
            it?.let {
                hideProgressBar()
                goToWeatherDetail(it)
                weatherConsultViewModel.selectedWeatherLiveData.postValue(null)
            }
        })

        weatherConsultViewModel.favoriteCitiesWeatherLiveData.observe(viewLifecycleOwner, {
            it?.let {
                binding.favoriteCitiesRecyclerView.apply {
                    adapter = FavoriteCitiesAdapter(
                        it,
                        ::onCitySelected
                    )
                }
            }
        })
    }

    private fun loadData() {
        weatherConsultViewModel.getFavoriteCitiesWeather()
    }

    private fun initConfiguration() {
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), GOOGLE_MAPS_API_KEY)
            Places.createClient(requireContext())
        }
    }

    private fun onSearchCalled() {
        val fields = listOf(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS_COMPONENTS)
        val intent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
            .build(requireContext())

        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }

    private fun getWeather(latitude: Double, longitude: Double, country: String) {
        showProgressBar()
        weatherConsultViewModel.getWeather(
            latitude,
            longitude,
            country
        )
    }

    private fun onCitySelected(weather: WeatherData) {
        getWeather(
            weather.lat ?: 0.0,
            weather.lon ?: 0.0,
            weather.country ?: ""
        )
    }

    private fun goToWeatherDetail(weather: WeatherData) {
        findNavController().navigate(
            R.id.action_weatherConsultFragment_to_weatherDetailFragment,
            bundleOf(
                "id" to weather.id.toString(),
                "latitude" to weather.lat.toString(),
                "longitude" to weather.lon.toString()
            )
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    data?.let {
                        val place = Autocomplete.getPlaceFromIntent(data)
                        val latLng = place.latLng
                        getWeather(
                            latLng?.latitude ?: 0.0,
                            latLng?.longitude ?: 0.0,
                            place.addressComponents?.asList()?.getOrNull(3)?.name ?: ""
                        )
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    data?.let {
                        val status = Autocomplete.getStatusFromIntent(data)
                    }
                }
                Activity.RESULT_CANCELED -> {

                }
            }
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }
}