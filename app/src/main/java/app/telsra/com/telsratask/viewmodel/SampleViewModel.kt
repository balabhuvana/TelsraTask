package viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import app.telsra.com.telsratask.model.ResponseData
import app.telsra.com.telsratask.restservice.CountryAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




/**
 * Created by bala on 4/12/18.
 */
class SampleViewModel : ViewModel() {

    private var countryList: MutableLiveData<ResponseData>? = null

    fun getCountryList(): LiveData<ResponseData> {
        if (countryList == null) {
            countryList = MutableLiveData<ResponseData>()
            loadCountryList()
        }

        return countryList as MutableLiveData<ResponseData>
    }

    private fun loadCountryList() {

        val BASE_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val countryApi = retrofit.create(CountryAPI::class.java)
        countryApi.countryData.enqueue(object : Callback<ResponseData> {
            override fun onFailure(call: Call<ResponseData>?, t: Throwable?) {
                try {
                    Log.d("---> ", t!!.localizedMessage)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            override fun onResponse(call: Call<ResponseData>?, response: Response<ResponseData>?) {
                val data = response!!.body()
                val sampledata = data!!.rows
                countryList!!.postValue(data)

            }
        })

    }

}