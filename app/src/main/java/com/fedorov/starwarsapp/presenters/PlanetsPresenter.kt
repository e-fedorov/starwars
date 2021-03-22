package com.fedorov.starwarsapp.presenters

import com.fedorov.starwarsapp.PlanetView
import com.fedorov.starwarsapp.interactors.PlanetsInteractor
import com.fedorov.starwarsapp.models.Planet

class PlanetsPresenter(var planetView: PlanetView?, val planetsInteractor: PlanetsInteractor) : PlanetsInteractor.OnPlanetListLoadedListener
{
    fun showPlanets()
    {
        planetView?.showProgress()
        planetsInteractor.loadPlanetList(this)
    }

    fun onDestroy()
    {
        planetView = null
    }
    override fun onSuccess(planets: List<Planet>)
    {
        planetView?.hideProgress()
        planetView?.showPlanets(planets)
    }

    override fun onError(text: String)
    {
        planetView?.showError(text)
    }
}