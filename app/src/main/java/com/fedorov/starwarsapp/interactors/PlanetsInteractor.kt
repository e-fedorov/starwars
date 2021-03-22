package com.fedorov.starwarsapp.interactors

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fedorov.starwarsapp.models.Planet
import org.json.JSONException
import org.json.JSONObject
import java.security.AccessControlContext


class PlanetsInteractor(private val context: Context)
{
    interface OnPlanetListLoadedListener
    {
        fun onSuccess(planets: List<Planet>)
        fun onError(text: String)
    }

    fun loadPlanetList(presenter: OnPlanetListLoadedListener)
    {
        var url = "https://swapi.dev/api/planets/?format=json"
        var stringRequest = StringRequest(Request.Method.GET, url,
        Response.Listener
        {
            try
            {
                val json = JSONObject(it)
                var planets = mutableListOf<Planet>()

                if(json.has("results"))
                {
                    var jsonPlanets = json.getJSONArray("results")
                    var index:Int=0;
                    while (index < jsonPlanets.length())
                    {
                        var jsonPlanet = jsonPlanets[index] as JSONObject

                        var name:String? = null
                        if(jsonPlanet.has("name"))
                        {
                            name = jsonPlanet.getString("name")
                        }

                        var url:String? = null
                        if(jsonPlanet.has("url"))
                        {
                            url = jsonPlanet.getString("url")
                        }

                        planets.add(Planet(name, url))
                        index ++
                    }
                }

                presenter.onSuccess(planets)
            }
            catch (ex: JSONException)
            {
                presenter.onError(ex.localizedMessage)
            }
        },
        Response.ErrorListener
        {
            presenter.onError(it.localizedMessage)
        })

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
}