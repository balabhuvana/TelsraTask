package app.telsra.com.telsratask.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.telsra.com.telsratask.R
import app.telsra.com.telsratask.adapter.CountryRecyclerAdapter
import app.telsra.com.telsratask.model.CountryData
import app.telsra.com.telsratask.model.ResponseData
import app.telsra.com.telsratask.presenter.CountryPresenter
import kotlinx.android.synthetic.main.fragment_telsra.*


/**
 * A simple [Fragment] subclass.
 */
class TelsraFragment : Fragment(), CountryView {

    private var filterCountryList: ArrayList<CountryData>? = null
    private var countryRecyclerAdapter: CountryRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_telsra, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        filterCountryList = ArrayList()

        countryRecyclerAdapter = CountryRecyclerAdapter(this@TelsraFragment.context, filterCountryList!!)
        val llm = LinearLayoutManager(this@TelsraFragment.context)
        llm.orientation = LinearLayoutManager.VERTICAL
        sampleRecyclerViewMvp.layoutManager = llm
        sampleRecyclerViewMvp.adapter = countryRecyclerAdapter

        val countryPresenter = CountryPresenter(this)
        countryPresenter.sampleCountires()

        swipeFreshLayout.setOnRefreshListener {
            countryPresenter.sampleCountires()
            swipeFreshLayout.isRefreshing = false
        }

    }

    override fun getCountryData(responseData: ResponseData) {

        filterCountryList!!.clear()

        (0 until responseData.rows.size)
                .filter { responseData.rows.get(it).title != null || responseData.rows.get(it).description != null }
                .forEach { filterCountryList!!.add(responseData.rows[it]) }

        countryRecyclerAdapter!!.notifyDataSetChanged()
        (activity as TeslraActivity).setActionBarTitle(responseData.title)

    }
}
