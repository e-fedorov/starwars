package com.fedorov.starwarsapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fedorov.starwarsapp.interactors.PlanetsInteractor
import com.fedorov.starwarsapp.models.Planet
import com.fedorov.starwarsapp.presenters.PlanetsPresenter
import com.fedorov.starwarsapp.R
import com.fedorov.starwarsapp.MainActivity
import com.fedorov.starwarsapp.adapters.PlanetsAdapter
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(),PlanetView
{
    private var adapter:PlanetsAdapter? = null
    private var presenter:PlanetsPresenter? = null
    lateinit var PlanetsList:RecyclerView
    lateinit var PlanetTitle: TextView
    lateinit var ProgressBar: ProgressBar
    lateinit var LoadBtn: Button
    lateinit var ProgressBarContainer: View

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = PlanetsPresenter(this, PlanetsInteractor(this.applicationContext))
        adapter = PlanetsAdapter(kotlin.collections.mutableListOf())

        PlanetsList = findViewById(R.id.planets_list)
        PlanetsList.layoutManager = LinearLayoutManager(this)
        PlanetsList.adapter = adapter

        PlanetTitle = findViewById(R.id.planets_title)

        ProgressBar = findViewById(R.id.planets_progressbar)
        ProgressBarContainer = findViewById(R.id.planets_progressbar_container)

        LoadBtn = findViewById(R.id.planets_loadbtn)
        LoadBtn.setOnClickListener(View.OnClickListener
        {
            presenter?.showPlanets()
        })
    }

    override fun showProgress()
    {
        adapter?.planets?.clear()
        adapter?.notifyDataSetChanged()
        ProgressBarContainer.visibility = View.VISIBLE
    }

    override fun hideProgress()
    {
        ProgressBarContainer.visibility = View.GONE
    }

    override fun showError(text: String)
    {
        Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
        Log.e("StarWars Error", text);
    }

    override fun showPlanets(planets: List<Planet>)
    {
        adapter?.planets?.clear()
        adapter?.planets?.addAll(planets)
        adapter?.notifyDataSetChanged()
    }
}