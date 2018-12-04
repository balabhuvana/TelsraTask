package app.telsra.com.telsratask.presenter

import android.util.Log
import app.telsra.com.telsratask.model.ResponseData
import app.telsra.com.telsratask.restservice.CountryService
import app.telsra.com.telsratask.view.CountryView
import app.telsra.com.telsratask.view.TelsraFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by bala on 25/11/18.
 */
class CountryPresenter(telsraFragment: TelsraFragment) {
    private var countryView: CountryView? = null
    private var countryService: CountryService? = null

    init {
        this.countryView = telsraFragment
        this.countryService = CountryService()
    }

    fun sampleCountires() {
        countryService!!.api.countryData.enqueue(object : Callback<ResponseData> {
            override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                val data = response.body()

                if (data != null) {
                    countryView!!.getCountryData(data)
                }
            }

            override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                try {
                    Log.d("---> ", t.localizedMessage)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        })
    }
}