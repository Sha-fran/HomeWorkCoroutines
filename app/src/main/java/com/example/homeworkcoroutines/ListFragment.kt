package com.example.homeworkcoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkcoroutines.databinding.ListFragmentLayoutBinding

class ListFragment : Fragment(), SuperheroRecyclerViewAdapter.OnItemClickListener{
    private lateinit var binding: ListFragmentLayoutBinding
    private var onItemClick:(item: DataClasses.Superheroes) -> Unit = {}
    private val superheroViewModel = ViewModelProvider(this).get(SuperheroViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ListFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = SuperheroRecyclerViewAdapter(onItemClickListener = this)

        superheroViewModel.getSuperheroes()

        superheroViewModel.uiState.observe(viewLifecycleOwner) {uiState ->
            when(uiState) {
                is SuperheroViewModel.UiState.Empty -> Unit
                is SuperheroViewModel.UiState.Success -> {
                    adapter.items = uiState.list as MutableList<DataClasses.Superheroes>
                    adapter.notifyDataSetChanged()
                    binding.superheroRecyclerview.adapter = adapter
                    binding.superheroRecyclerview.layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }

    override fun onItemClick(item: DataClasses.Superheroes) {
        onItemClick.invoke(item)
    }

    fun setItemClickListener(lambda: (item: DataClasses.Superheroes)-> Unit) {
        onItemClick = lambda
    }
}