package org.itstep.liannoi.weatherforecast.presentation.forecasts.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.view.clicks
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.itstep.liannoi.weatherforecast.R
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.common.exceptions.DetailForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.ForecastRepository
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.current.CurrentQuery
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.current.models.CurrentForecastModel
import org.itstep.liannoi.weatherforecast.presentation.forecasts.list.ForecastsListActivity

class MainActivity : AppCompatActivity(), CurrentQuery.Handler {
    private val repository: ForecastRepository?
        get() = ForecastRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentForecastButton.clicks()
            .map { cityInput.text.toString() }
            .filter { it.isNotEmpty() }
            .subscribe { updateCurrentForecast(it) }

        listForecastsButton.clicks()
            .map { cityInput.text.toString() }
            .filter { it.isNotEmpty() }
            .subscribe { showFiveDaysActivity(it) }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helpers
    ///////////////////////////////////////////////////////////////////////////

    private fun updateCurrentForecast(it: String) {
        repository?.getCurrent(CurrentQuery(it), this)
    }

    private fun showFiveDaysActivity(city: String) {
        val intent = Intent(this, ForecastsListActivity::class.java).apply {
            putExtra(ApplicationDefaults.EXTRA_FORECASTS_FIVE_DAYS, city)
        }

        startActivity(intent)
        finish()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Forecasts - DetailQuery
    ///////////////////////////////////////////////////////////////////////////

    override fun onSuccess(forecast: CurrentForecastModel) {
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
