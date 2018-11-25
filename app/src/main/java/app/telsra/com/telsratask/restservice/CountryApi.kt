package app.telsra.com.telsratask.restservice

import app.telsra.com.telsratask.model.ResponseData
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by bala on 25/11/18.
 */

interface CountryAPI {
    @get:GET("facts.json")
    val countryData: Call<ResponseData>
}
