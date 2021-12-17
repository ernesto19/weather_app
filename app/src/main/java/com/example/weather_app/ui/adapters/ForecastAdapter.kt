package com.example.weather_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather_app.R
import com.example.weather_app.databinding.ForecastItemBinding
import com.example.weather_app.domain.entities.Forecast

class ForecastAdapter(val list: List<Forecast>):
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding: ForecastItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.forecast_item,
            parent,
            false
        )

        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ForecastViewHolder(private val binding: ForecastItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: Forecast) {
            binding.item = forecast

            Glide.with(itemView.context).load("https://openweathermap.org/img/w/${forecast.icon}.png")
                .into(binding.ivIcon)
        }
    }
}