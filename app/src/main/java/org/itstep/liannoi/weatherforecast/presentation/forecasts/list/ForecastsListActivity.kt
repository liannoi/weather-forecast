package org.itstep.liannoi.weatherforecast.presentation.forecasts.list

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.github.karczews.rxbroadcastreceiver.RxBroadcastReceivers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_forecasts_list.*
import org.itstep.liannoi.weatherforecast.R
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_CITY
import org.itstep.liannoi.weatherforecast.application.ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_KEYCODE_STOP
import org.itstep.liannoi.weatherforecast.application.storage.forecasts.ForecastRepository
import org.itstep.liannoi.weatherforecast.infrastructure.CarefulCapitalize
import org.itstep.liannoi.weatherforecast.infrastructure.DateTimeFormatter
import org.itstep.liannoi.weatherforecast.presentation.forecasts.services.PresentationForecastsService
import java.util.concurrent.TimeUnit

class ForecastsListActivity : AppCompatActivity() {

    private val repository: ForecastRepository?
        get() = ForecastRepository.getInstance()

    private val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecasts_list)

        setupRecyclerLayout()
        prepareTitle()
        listenReceiver()
        startForecastsService()
        mockAdapter()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helpers
    ///////////////////////////////////////////////////////////////////////////

    private fun prepareTitle() {
        with(CarefulCapitalize()) {
            forecastCityText.text =
                intent.getStringExtra(ApplicationDefaults.EXTRA_FORECASTS_FIVE_DAYS)?.careful()
        }
    }

    private fun setupRecyclerLayout() {
        forecastsRecyclerView.layoutManager = StaggeredGridLayoutManager(
            ApplicationDefaults.COUNT_CARDS_FORECASTS_FIVE_DAYS,
            StaggeredGridLayoutManager.VERTICAL
        )
    }

    private fun listenReceiver() {
        RxBroadcastReceivers.fromIntentFilter(this, IntentFilter(ACTION_FORECASTS_FIVE_DAYS))
            .delay(1, TimeUnit.MICROSECONDS, Schedulers.trampoline())
            .subscribe {

                // Refactoring and implementation of the "Strategy" pattern is required!

                val keycode: Int =
                    it.getIntExtra(ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_STATUS, 0)

                if (keycode == ACTION_FORECASTS_FIVE_DAYS_KEYCODE_STOP) {
                    val adapter = ForecastsListCardAdapter(
                        it.getParcelableExtra(ApplicationDefaults.ACTION_FORECASTS_FIVE_DAYS_RESULT)!!,
                        DateTimeFormatter()
                    )

                    forecastsRecyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            .follow()
    }

    private fun mockAdapter() {
        forecastsRecyclerView.adapter = ForecastsListCardAdapter(null, DateTimeFormatter())
    }

    private fun startForecastsService() {
        startService(
            Intent(this, PresentationForecastsService::class.java).putExtra(
                ACTION_FORECASTS_FIVE_DAYS_CITY,
                forecastCityText.text.toString()
            )
        )
    }

    ///////////////////////////////////////////////////////////////////////////
    // Dispose
    ///////////////////////////////////////////////////////////////////////////

    override fun onStop() {
        super.onStop()
        repository?.stop()
        disposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        repository?.destroy()
        disposable.dispose()
    }

    private fun Disposable.follow() {
        disposable.add(this)
    }
}
