package dev.dslam.newsapi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.dslam.newsapi.Constants
import dev.dslam.newsapi.R
import dev.dslam.newsapi.viewModels.HeadlinesFragmentViewModel

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {

    private val viewModel by viewModels<HeadlinesFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(this.requireContext(), "initViewModel", Toast.LENGTH_SHORT).show()
        initViewModel()
    }

    private fun initViewModel() {
        Log.d("initViewModel", "ViewModel Start")
        Toast.makeText(this.requireContext(), "initViewModel", Toast.LENGTH_SHORT).show()
        viewModel.getHeadlines().observe(viewLifecycleOwner, {
            Log.d("initViewModel", it.toString())
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this.requireContext(), "Данные получены", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this.requireContext(), "Упс, что-то пошло не так :-(", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.loadHeadlines(search = Constants.TOPIC, apiKey = Constants.API_KEY)
    }
}