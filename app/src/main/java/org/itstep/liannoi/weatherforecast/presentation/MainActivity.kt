package org.itstep.liannoi.weatherforecast.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.itstep.liannoi.weatherforecast.R
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.ForecastRepository
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.models.ForecastModel
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.DetailQuery

class MainActivity : AppCompatActivity(), DetailQuery.Handler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ForecastRepository.getInstance()
            ?.current(DetailQuery("Kyiv", "d1bd10872a0a7d4757bd4670cbc32ef4", "metric"), this)
    }

    override fun onSuccess(forecast: ForecastModel) {
        Toast.makeText(this, forecast.main.temp.toString(), Toast.LENGTH_SHORT).show();
        Log.d("onSuccess: ", forecast.toString());
    }

    override fun onError(e: Exception) {
        Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();
        Log.d("onError: ", e.message.toString());
    }
}
