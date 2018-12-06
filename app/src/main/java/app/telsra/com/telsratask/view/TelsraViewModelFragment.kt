package app.telsra.com.telsratask.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import kotlinx.android.synthetic.main.fragment_telsra_view_model.*
import viewmodel.SampleViewModel


/**
 * A simple [Fragment] subclass.
 */
class TelsraViewModelFragment : Fragment() {

    var countryRecyclerAdapter: CountryRecyclerAdapter? = null
    var filterCountryList: ArrayList<CountryData>? = null
    var swipeRefreshCountryList: ArrayList<CountryData>? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_telsra_view_model, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeFreshLayoutViewModel.setOnRefreshListener {
            countryRecyclerAdapter!!.clear()
            countryRecyclerAdapter!!.addAll(this.swipeRefreshCountryList!!)
            swipeFreshLayoutViewModel.isRefreshing = false
        }

        val sampleViewModel = ViewModelProviders.of(this).get(SampleViewModel::class.java)
        sampleViewModel.getCountryList().observe(this, object : Observer<ResponseData> {
            override fun onChanged(t: ResponseData?) {

                (activity as TeslraActivity).setActionBarTitle(t!!.title)

                filterCountryList = ArrayList()
                swipeRefreshCountryList = ArrayList()

                val countryList = t.rows

                for (i in 0 until countryList.size) {
                    if (countryList.get(i).title != null || countryList.get(i).description != null) {
                        filterCountryList!!.add(countryList[i])
                        swipeRefreshCountryList!!.add(countryList[i])
                    }
                }

                countryRecyclerAdapter = CountryRecyclerAdapter(this@TelsraViewModelFragment.context, filterCountryList!!)
                val llm = LinearLayoutManager(this@TelsraViewModelFragment.context)
                llm.orientation = LinearLayoutManager.VERTICAL
                sampleRecyclerViewModel.layoutManager = llm
                sampleRecyclerViewModel.adapter = countryRecyclerAdapter
            }
        })
    }

}// Required empty public constructor
