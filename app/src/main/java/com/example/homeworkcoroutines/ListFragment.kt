package com.example.homeworkcoroutines


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkcoroutines.databinding.ListFragmentLayoutBinding
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListFragment : Fragment(), SuperheroRecyclerViewAdapter.OnItemClickListener{
    private lateinit var binding: ListFragmentLayoutBinding
    private var onItemClick:(item: DataClasses.Superheroes) -> Unit = {}

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

        val superheroViewModel:SuperheroViewModel = ViewModelProvider(this).get(SuperheroViewModel::class.java)
        val adapter = SuperheroRecyclerViewAdapter(onItemClickListener = this)
        binding.superheroRecyclerview.adapter = adapter
        binding.superheroRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        superheroViewModel.uiState.observe(viewLifecycleOwner) {uiState ->
            when(uiState) {
                is UiState.EmptyList -> Unit
                is UiState.FilledList -> {
                    adapter.items =uiState.list
                    adapter.notifyDataSetChanged()
                }
                is UiState.Error -> Unit
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