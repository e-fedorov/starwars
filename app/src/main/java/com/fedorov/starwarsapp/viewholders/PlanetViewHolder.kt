package com.fedorov.starwarsapp.viewholders

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.fedorov.starwarsapp.R
import com.fedorov.starwarsapp.models.Planet


class PlanetViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener
{
    private lateinit var bindPlanet: Planet
    private var view: View = v
    private var name: TextView? = null
    private var button: Button? = null

    init
    {
        name = view?.findViewById(R.id.planet_name)
        button = view?.findViewById(R.id.planet_showbth)

        v.setOnClickListener(this)
    }

    fun bindPlanet(planet: Planet)
    {
        bindPlanet = planet
        name?.text = bindPlanet.Name
        button?.setOnClickListener(View.OnClickListener
        {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(bindPlanet.Url)
            view.context.startActivity(i)
        })
    }

    override fun onClick(v: View)
    {
        Log.d("RecyclerView", "CLICK!")
    }

    companion object
    {
        private val KEY = "planet"
    }
}