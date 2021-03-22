package com.fedorov.starwarsapp

import com.fedorov.starwarsapp.models.Planet

interface PlanetView
{
    fun showProgress()
    fun hideProgress()
    fun showError(text:String)
    fun showPlanets(planets:List<Planet>)
}