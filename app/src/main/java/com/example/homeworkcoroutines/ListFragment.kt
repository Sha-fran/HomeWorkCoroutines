package com.example.homeworkcoroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkcoroutines.databinding.ListFragmentLayoutBinding
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

        val api =  ApiClient.client.create(ApiInterface::class.java)
        val adapter = SuperheroRecyclerViewAdapter(onItemClickListener = this)

        api.getSuperheroes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.items = it
                adapter.notifyDataSetChanged()
                binding.superheroRecyclerview.adapter = adapter
            },
                {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                })
        binding.superheroRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClick(item: DataClasses.Superheroes) {
        onItemClick.invoke(item)
    }

    fun setItemClickListener(lambda: (item: DataClasses.Superheroes)-> Unit) {
        onItemClick = lambda
    }
}