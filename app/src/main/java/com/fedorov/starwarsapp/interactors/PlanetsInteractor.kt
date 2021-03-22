package com.fedorov.starwarsapp.interactors

import android.content.Context
import android.os.Handler
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.fedorov.starwarsapp.models.Planet
import org.json.JSONException
import org.json.JSONObject


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
            val t = Thread(Runnable
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
                    val mainHandler: Handler = Handler(context.mainLooper)
                    mainHandler.post(Runnable
                    {
                        presenter.onSuccess(planets)
                    })

                }
                catch (e: InterruptedException)
                {
                    val mainHandler: Handler = Handler(context.mainLooper)
                    mainHandler.post(Runnable
                    {
                        presenter.onError(e.localizedMessage)
                    })
                }
            })
            t.start()
        },
        Response.ErrorListener
        {
            presenter.onError(it.localizedMessage)
        })

        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(stringRequest)
    }
}
