package dev.dslam.newsapi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.dslam.newsapi.Constants
import dev.dslam.newsapi.adapters.NewsListAdapter
import dev.dslam.newsapi.databinding.FragmentHeadlinesBinding
import dev.dslam.newsapi.viewModels.HeadlinesFragmentViewModel
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HeadlinesFragment : Fragment() {

    private val viewModel by viewModels<HeadlinesFragmentViewModel>()
    private var _binding: FragmentHeadlinesBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsListAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initViewModel()
    }

    private fun initRecycler() {
        binding.rvHeadlinesList.apply {
            layoutManager = LinearLayoutManager(this.context)
            newsListAdapter = NewsListAdapter()
            adapter = newsListAdapter
        }
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.loadHeadlines(search = Constants.TOPIC, apiKey = Constants.API_KEY)
                .collectLatest {
                    newsListAdapter.submitData(it)
                }
        }
    }
}