package com.example.homeworkcoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.homeworkcoroutines.databinding.DetailsFragmentLayoutBinding

class DetailsFragment:Fragment() {
    private lateinit var binding: DetailsFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentLayoutBinding.inflate(inflater, container, false)
        binding.superheroName.text = requireArguments().getString("SuperheroName")
        binding.superheroSlug.text = requireArguments().getString("SuperheroSlug")
        binding.superheroGender.text = requireArguments().getString("SuperheroGender")
        binding.superheroRace.text = requireArguments().getString("SuperheroRace")
        binding.superheroStats.text = "Stats: \n\n" +
                "- Intelligence: ${requireArguments().getString("SuperheroIntelligence")}\n\n" +
                "- Strength: ${requireArguments().getString("SuperheroStrength")}\n\n" +
                "- Speed: ${requireArguments().getString("SuperheroSpeed")}\n\n" +
                "- Durability: ${requireArguments().getString("SuperheroDurability")}\n\n" +
                "- Power: ${requireArguments().getString("SuperheroPower")}"
        Glide.with(binding.superheroImageDetails)
            .load(requireArguments().getString("SuperheroImage"))
            .into(binding.superheroImageDetails)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun newInstance(
        superheroName:String?,
        superheroSlug:String?,
        superheroGender:String?,
        superheroRace:String?,
        superheroIntelligence:Int?,
        superheroStrength:Int?,
        superheroSpeed:Int?,
        superheroDurability:Int?,
        superheroPower:Int?,
        superheroImage:String?
    ):DetailsFragment {
        val args = Bundle().apply {
            putString("SuperheroName", superheroName)
            putString("SuperheroSlug", superheroSlug)
            putString("SuperheroGender", superheroGender)
            putString("SuperheroRace", superheroRace)
            putString("SuperheroIntelligence", superheroIntelligence.toString())
            putString("SuperheroStrength", superheroStrength.toString())
            putString("SuperheroSpeed", superheroSpeed.toString())
            putString("SuperheroDurability", superheroDurability.toString())
            putString("SuperheroPower", superheroPower.toString())
            putString("SuperheroImage", superheroImage)
        }

        val fragment = DetailsFragment()
        fragment.arguments = args
        return fragment
    }
}