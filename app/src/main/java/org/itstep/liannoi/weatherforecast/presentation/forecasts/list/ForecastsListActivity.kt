package org.itstep.liannoi.weatherforecast.presentation.forecasts.list

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_forecasts_list.*
import org.itstep.liannoi.weatherforecast.R
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.common.exceptions.FiveDaysForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.ForecastRepository
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.FiveDaysQuery
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models.FiveDaysForecastModel
import org.itstep.liannoi.weatherforecast.infrastructure.CarefulCapitalize
import org.itstep.liannoi.weatherforecast.infrastructure.DateTimeFormatter

class ForecastsListActivity : AppCompatActivity(), FiveDaysQuery.Handler {
    private val repository: ForecastRepository?
        get() = ForecastRepository.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecasts_list)

        forecastsRecyclerView.layoutManager = StaggeredGridLayoutManager(
            ApplicationDefaults.COUNT_CARDS_FORECASTS_FIVE_DAYS,
            StaggeredGridLayoutManager.VERTICAL
        )

        with(CarefulCapitalize()) {
            forecastCityText.text =
                intent.getStringExtra(ApplicationDefaults.EXTRA_FORECASTS_FIVE_DAYS)?.careful()
        }

        repository?.getFiveDays(FiveDaysQuery(forecastCityText.text.toString()), this)
    }

    ///////////////////////////////////////////////////////////////////////////
    // Forecasts - FiveDaysQuery
    ///////////////////////////////////////////////////////////////////////////

    override fun onSuccess(model: FiveDaysForecastModel) {
        forecastsRecyclerView.adapter = ForecastsListCardAdapter(model, DateTimeFormatter())
    }

    override fun onError(exception: FiveDaysForecastException) {
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
