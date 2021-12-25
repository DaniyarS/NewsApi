package dev.dslam.newsapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.dslam.newsapi.Constants
import dev.dslam.newsapi.R
import dev.dslam.newsapi.viewModels.EverythingFragmentViewModel

@AndroidEntryPoint
class EverythingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_headlines, container, false)
    }

    private val viewModel by viewModels<EverythingFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {

        viewModel.getEverythingObserver().observe(viewLifecycleOwner, {
            if (!it.isNullOrEmpty()) {
                Toast.makeText(this.requireContext(), "Данные получены", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this.requireContext(), "Упс, что-то пошло не так :-(", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.loadEverything(search = Constants.TOPIC, apiKey = Constants.API_KEY)
    }
}