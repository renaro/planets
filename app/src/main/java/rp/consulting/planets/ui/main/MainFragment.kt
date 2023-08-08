package rp.consulting.planets.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import rp.consulting.planets.R

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: PlanetListViewModel by viewModels()

    private val clickListener = { planet: PlanetData ->
        // TODO Implementar navegacao aqui
        Toast.makeText(requireContext(), "Planeta: ${planet.name}", Toast.LENGTH_SHORT).show()
    }
    private val adapter = PlanetsAdapter(clickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = view.findViewById<RecyclerView>(R.id.list)
        val loading = view.findViewById<ProgressBar>(R.id.loading)
        val error = view.findViewById<ImageView>(R.id.error)
        list.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Content -> {
                    adapter.setData(state.list)
                    list.adapter = adapter
                    loading.isVisible = false
                    list.isVisible = true
                    error.isVisible = false
                }

                State.Error -> {
                    loading.isVisible = false
                    list.isVisible = false
                    error.isVisible = true
                }

                State.Loading -> {
                    loading.isVisible = true
                    list.isVisible = false
                    error.isVisible = false
                }
            }
        }
        viewModel.loadData()
    }
}