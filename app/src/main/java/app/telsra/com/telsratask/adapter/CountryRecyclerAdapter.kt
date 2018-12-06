package app.telsra.com.telsratask.adapter

/**
 * Created by bala on 4/12/18.
 */
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.telsra.com.telsratask.R
import app.telsra.com.telsratask.model.CountryData
import com.bumptech.glide.Glide


class CountryRecyclerAdapter(var mCtx: Context, var notesList: ArrayList<CountryData>) : RecyclerView.Adapter<CountryRecyclerAdapter.SampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.telsra_data_row, parent, false)
        return SampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        val country = notesList[position]

        Glide.with(mCtx)
                .load(country.imageHref)
                .into(holder.countryImage)

        holder.tvTitle!!.setText(country.title)
        holder.tvContent!!.setText(country.description)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class SampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvTitle: TextView? = null
        var tvContent: TextView? = null
        var countryImage: ImageView? = null

        init {

            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvContent = itemView.findViewById(R.id.tvContent)
            countryImage = itemView.findViewById(R.id.countryImage)
        }
    }

    // Clean all elements of the recycler
    fun clear() {
        notesList.clear()
        notifyDataSetChanged()
    }

    fun addAll(list: ArrayList<CountryData>) {
        notesList.addAll(list)
        notifyDataSetChanged()
    }
}