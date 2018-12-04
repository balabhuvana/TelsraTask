package app.mysecond.com.sampletelsarataskjava

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import app.telsra.com.telsratask.R
import app.telsra.com.telsratask.model.CountryData
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by bala on 22/11/18.
 */
class CountryAdapter(context: Context, private var notesList: ArrayList<CountryData>) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        val view: View?
        val vh: ViewHolder

        if (convertView == null) {
            view = layoutInflater!!.inflate(R.layout.telsra_data_row, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
            Log.i("JSA", "set Tag for ViewHolder, position: " + position)
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }


        vh.tvTitle.text = notesList[position].title
        vh.tvContent.text = notesList[position].description
        Picasso.get().load(notesList[position].imageHref).placeholder(R.drawable.ic_launcher_background).into(vh.countryImage)

        return view
    }

    override fun getItem(position: Int): Any {
        return notesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return notesList.size
    }

    init {
        this.layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}

private class ViewHolder(view: View?) {
    val tvTitle: TextView
    val tvContent: TextView
    var countryImage: ImageView? = null

    init {
        this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
        this.tvContent = view.findViewById(R.id.tvContent) as TextView
        this.countryImage = view.findViewById(R.id.countryImage)
    }
}
