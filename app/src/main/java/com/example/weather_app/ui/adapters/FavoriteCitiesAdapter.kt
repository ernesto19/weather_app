package com.example.weather_app.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app.R
import com.example.weather_app.databinding.FavoriteCityItemBinding
import com.example.weather_app.domain.entities.WeatherData

class FavoriteCitiesAdapter(
    val list: List<WeatherData>,
    val onCitySelected: (weather: WeatherData) -> Unit
): RecyclerView.Adapter<FavoriteCitiesAdapter.FavoriteCitiesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteCitiesViewHolder {
        val binding: FavoriteCityItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.favorite_city_item,
            parent,
            false
        )

        return FavoriteCitiesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteCitiesViewHolder, position: Int) {
        holder.bind(list[position], holder.itemView.context)
    }

    override fun getItemCount(): Int = list.size

    inner class FavoriteCitiesViewHolder(private val binding: FavoriteCityItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherData: WeatherData, context: Context) {
            binding.item = weatherData
            val temperature = weatherData.main?.temperature ?: 30

            binding.cityCardView.setOnClickListener {
                onCitySelected(weatherData)
            }
            binding.container.background = ContextCompat.getDrawable(
                context,
                when {
                    temperature < 12 -> R.drawable.rainy_weather_background
                    temperature in 12..15 -> R.drawable.cloudy_weather_background
                    temperature in 16..22 -> R.drawable.cold_weather_background
                    else -> R.drawable.hot_weather_background
                }
            )
        }
    }
}