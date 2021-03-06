package app.telsra.com.telsratask.restservice

import app.telsra.com.telsratask.util.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by bala on 25/11/18.
 */
class CountryService {
    private var retrofit: Retrofit? = null

    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    val api: CountryAPI
        get() {


            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }

            return retrofit!!.create(CountryAPI::class.java)
        }
}