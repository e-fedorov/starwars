package com.fedorov.starwarsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fedorov.starwarsapp.R
import com.fedorov.starwarsapp.models.Planet
import com.fedorov.starwarsapp.viewholders.PlanetViewHolder

class PlanetsAdapter(public var planets: MutableList<Planet>): RecyclerView.Adapter<PlanetViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder
    {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_planet, parent, false)
        return PlanetViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        return planets.count()
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int)
    {
        val planet = planets[position]
        holder.bindPlanet(planet)
    }
}
