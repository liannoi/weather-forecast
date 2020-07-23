package org.itstep.liannoi.weatherforecast.presentation.forecasts.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_CITY
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_KEYCODE_START
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_KEYCODE_STOP
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_RESULT
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_STATUS
import org.itstep.liannoi.weatherforecast.application.common.exceptions.FiveDaysForecastException
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.ForecastRepository
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.FiveDaysQuery
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.queries.fivedays.models.FiveDaysForecastModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PresentationForecastsService : Service() {

    inner class Task(
        private val id: Int,
        private val city: String
    ) : Runnable,
        FiveDaysQuery.Handler {

        private lateinit var intent: Intent

        override fun run() {
            intent = Intent(ACTION_FORECASTS_FIVE_DAYS)
                .putExtra(
                    ACTION_FORECASTS_FIVE_DAYS_STATUS,
                    ACTION_FORECASTS_FIVE_DAYS_KEYCODE_START
                )

            sendBroadcast(intent)
            repository?.getFiveDays(FiveDaysQuery(city), this)
        }

        override fun onSuccess(model: FiveDaysForecastModel) {
            intent.putExtra(
                ACTION_FORECASTS_FIVE_DAYS_STATUS,
                ACTION_FORECASTS_FIVE_DAYS_KEYCODE_STOP
            )

            intent.putExtra(ACTION_FORECASTS_FIVE_DAYS_RESULT, model)
            sendBroadcast(intent)
            stopSelfResult(id)
        }

        override fun onError(exception: FiveDaysForecastException) {
            Log.d("onError: ", exception.message.toString())
        }
    }

    private lateinit var executorService: ExecutorService
    private val repository: ForecastRepository?
        get() = ForecastRepository.getInstance()

    override fun onCreate() {
        super.onCreate()

        executorService = Executors.newFixedThreadPool(2)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val city: String = intent.getStringExtra(ACTION_FORECASTS_FIVE_DAYS_CITY)!!
        executorService.execute(Task(startId, city))

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
