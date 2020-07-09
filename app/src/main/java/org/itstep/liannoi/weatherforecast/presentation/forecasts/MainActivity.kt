package org.itstep.liannoi.weatherforecast.presentation.forecasts

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.view.clicks
import com.squareup.picasso.Picasso
import com.trello.rxlifecycle4.android.lifecycle.kotlin.bindToLifecycle
import kotlinx.android.synthetic.main.activity_main.*
import org.itstep.liannoi.weatherforecast.R
import org.itstep.liannoi.weatherforecast.application.common.exceptions.DetailForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.ForecastRepository
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.models.ForecastModel
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.DetailQuery

class MainActivity : AppCompatActivity(), DetailQuery.Handler {
    private val repository: ForecastRepository?
        get() = ForecastRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentForecastButton.clicks()
            .map { cityInput.text.toString() }
            .filter { it.isNotEmpty() }
            .bindToLifecycle(this)
            .subscribe { repository?.getCurrent(DetailQuery(it), this) }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Forecasts - DetailQuery
    ///////////////////////////////////////////////////////////////////////////

    override fun onSuccess(forecast: ForecastModel) {
        Picasso.get().load(forecast.iconPath).into(forecastImage)
        forecastText.text = forecast.toShortString()
    }

    override fun onError(exception: DetailForecastException) {
        val message = exception.message.toString()
        Log.d("onError: ", message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Dispose
    ///////////////////////////////////////////////////////////////////////////

    override fun onStop() {
        super.onStop()
        repository?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        repository?.destroy()
    }
}
