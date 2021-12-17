package com.example.weather_app.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weather_app.R
import com.example.weather_app.databinding.ForecastHourlyItemBinding
import com.example.weather_app.domain.entities.ForecastHourly

class ForecastHourlyAdapter(val list: List<ForecastHourly>):
    RecyclerView.Adapter<ForecastHourlyAdapter.ForecastHourlyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastHourlyViewHolder {
        val binding: ForecastHourlyItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.forecast_hourly_item,
            parent,
            false
        )

        return ForecastHourlyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastHourlyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ForecastHourlyViewHolder(private val binding: ForecastHourlyItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(forecast: ForecastHourly) {
            binding.item = forecast

            Glide.with(itemView.context).load("https://openweathermap.org/img/w/${forecast.icon}.png")
                .into(binding.ivIcon)
        }
    }
}