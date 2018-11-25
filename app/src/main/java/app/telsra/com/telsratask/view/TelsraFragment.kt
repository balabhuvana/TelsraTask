package app.telsra.com.telsratask.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.mysecond.com.sampletelsarataskjava.CountryAdapter
import app.telsra.com.telsratask.R
import app.telsra.com.telsratask.model.CountryData
import app.telsra.com.telsratask.model.ResponseData
import app.telsra.com.telsratask.presenter.CountryPresenter
import kotlinx.android.synthetic.main.fragment_telsra.*


/**
 * A simple [Fragment] subclass.
 */
class TelsraFragment : Fragment(), CountryView {

    private var countryList: ArrayList<CountryData>? = null
    private var countryAdapter: CountryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_telsra, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryList = ArrayList()
        countryAdapter = CountryAdapter(context, countryList!!)
        telsraListView.adapter = countryAdapter

        val countryPresenter = CountryPresenter(this)
        countryPresenter.sampleCountires()

        swipeFreshLayout.setOnRefreshListener {
            Log.d("---> ", "onRefresh")
            countryPresenter.sampleCountires()
            swipeFreshLayout.isRefreshing = false
        }

    }

    override fun getCountryData(responseData: ResponseData) {

        countryList!!.clear()

        for (i in 0 until responseData.rows!!.size) {
            if (responseData.rows.get(i).title != null || responseData.rows.get(i).description != null) {
                countryList!!.add(responseData.rows.get(i))
            }
        }

        countryAdapter!!.notifyDataSetChanged()
        (activity as TeslraActivity).setActionBarTitle(responseData.title!!)

    }
}
