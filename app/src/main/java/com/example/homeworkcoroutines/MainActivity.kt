package com.example.homeworkcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homeworkcoroutines.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thread {
            Thread.sleep(300)
        }

        val listFragment = supportFragmentManager.findFragmentById(R.id.fragment_in_main_activity) as ListFragment

        listFragment.setItemClickListener {
            val superHero = it

            val bundle = Bundle()
            bundle.putSerializable("superhero", superHero)

            val detailsFragment = DetailsFragment().newInstance(
                superheroName = it.name,
                superheroSlug = it.slug,
                superheroGender = it.appearance?.gender,
                superheroRace = it.appearance?.race,
                superheroIntelligence = it.powerstats?.intelligence,
                superheroStrength = it.powerstats?.strength,
                superheroSpeed = it.powerstats?.speed,
                superheroDurability = it.powerstats?.durability,
                superheroPower = it.powerstats?.power,
                superheroImage = it.images?.lg)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_in_main_activity, detailsFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
