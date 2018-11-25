package app.telsra.com.telsratask.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.telsra.com.telsratask.R

class TeslraActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telsra)

        supportFragmentManager.beginTransaction().replace(R.id.telsraFrameLayout, TelsraFragment()).commit()
    }


    fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }
}
