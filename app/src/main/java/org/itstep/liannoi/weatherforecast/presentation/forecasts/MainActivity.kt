package org.itstep.liannoi.weatherforecast.presentation.forecasts

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.view.clicks
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.itstep.liannoi.weatherforecast.R
import org.itstep.liannoi.weatherforecast.application.common.exceptions.DetailForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.ForecastRepository
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.models.ForecastModel
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.DetailQuery

class MainActivity : AppCompatActivity(), DetailQuery.Handler {
    private val composite: CompositeDisposable = CompositeDisposable()

    private val repository: ForecastRepository?
        get() = ForecastRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentForecastButton.clicks()
            .map { cityInput.text.toString() }
            .filter { it.isNotEmpty() }
            .subscribe { repository?.getCurrent(DetailQuery(it), this) }
            .follow()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Forecasts - DetailQuery (Handler)
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
        repository?.stop()
        stop()
        super.onStop()
    }

    override fun onDestroy() {
        repository?.destroy()
        destroy()
        super.onDestroy()
    }

    private fun stop() {
        composite.clear()
    }

    private fun destroy() {
        composite.dispose()
    }

    private fun Disposable.follow() {
        composite.add(this)
    }
}
