package org.itstep.liannoi.weatherforecast.presentation.forecasts.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.adapter_card_forecasts_list.view.*
import org.itstep.liannoi.weatherforecast.R
import org.itstep.liannoi.weatherforecast.application.common.interfaces.Formatter
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models.FiveDaysForecastModel
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models.ListModel
import org.itstep.liannoi.weatherforecast.infrastructure.CarefulCapitalize

class ForecastsListCardAdapter constructor(
    private val fiveDaysForecastModel: FiveDaysForecastModel,
    private val dateTimeFormatter: Formatter
) : RecyclerView.Adapter<ForecastsListCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(container.context)
            .inflate(R.layout.adapter_card_forecasts_list, container, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fiveDaysForecastModel.list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = fiveDaysForecastModel.list[position]
        Picasso.get().load(model.iconPath).into(holder.forecastIcon)
        holder.detailsText.text = toTemperatureDateString(model)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helpers
    ///////////////////////////////////////////////////////////////////////////

    private fun toTemperatureDateString(model: ListModel): String {
        val formattedDate = dateTimeFormatter.format(model.dt_txt)

        val formattedHours = when {
            formattedDate.hour < 10 -> "0${formattedDate.hour}"
            else -> formattedDate.hour.toString()
        }

        var date: String
        val description: String

        with(CarefulCapitalize()) {
            date = "${formattedDate.dayOfMonth} ${formattedDate.month.name.careful()}"
            description = "${model.weather[0].description.careful()}\n"
        }

        val time = "${formattedHours}:00"
        val temperature = "${model.main.temp}°"

        return "$description$temperature ($date $time)"
    }

    class ViewHolder(container: View) : RecyclerView.ViewHolder(container) {
        val forecastIcon: ImageView = container.forecastIcon
        val detailsText: TextView = container.detailsText
    }
}
