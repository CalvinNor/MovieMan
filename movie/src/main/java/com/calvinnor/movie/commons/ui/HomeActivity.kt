package com.calvinnor.movie.commons.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.calvinnor.core.ui.BaseActivity
import com.calvinnor.movie.R
import com.calvinnor.movie.commons.util.NetworkUtils.getNetworkLiveData
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getNetworkLiveData(applicationContext).observe(this, Observer { isConnected ->
            no_internet_warning.apply {
                when (isConnected) {
                    true -> if (isVisible) visibility = GONE
                    false -> if (isGone) visibility = VISIBLE
                }
            }
        })
    }
}
