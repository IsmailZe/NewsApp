package com.test.prismamediatesttechnique.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.test.prismamediatesttechnique.R
import com.test.prismamediatesttechnique.data.models.State
import com.test.prismamediatesttechnique.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _viewBinding: FragmentNewsListBinding? = null
    private val viewBinding: FragmentNewsListBinding get() = _viewBinding!!
    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentNewsListBinding.inflate(
            inflater,
            container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsViewModel.getNews()
            }
        }
        val adapter = NewsListAdapter {
            val direction = NewsListFragmentDirections
                .actionNewsListFragmentToNewsDetailsFragment(it)
            findNavController().navigate(direction)
        }
        viewBinding.rvNews.adapter = adapter
        newsViewModel.newsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is State.LoadingState -> {
                    viewBinding.pbLoading.visibility = View.VISIBLE
                }
                is State.ErrorState -> {
                    viewBinding.pbLoading.visibility = View.GONE
                    Toast.makeText(
                        context?.applicationContext,
                        it.exception.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                is State.DataState -> {
                    viewBinding.pbLoading.visibility = View.GONE
                    adapter.submitList(it.data)
                }
            }
        }

        val favouriteAdapter = NewsListAdapter {
            val direction = NewsListFragmentDirections
                .actionNewsListFragmentToNewsDetailsFragment(it)
            findNavController().navigate(direction)
        }

        adapter.onFavouriteClick = {
            newsViewModel.refreshFavourites(it)
        }

        favouriteAdapter.onFavouriteClick = {
            newsViewModel.refreshFavourites(it)
        }

        newsViewModel.favouriteNewsLiveData.observe(viewLifecycleOwner){
            viewBinding.pbLoading.visibility = View.GONE
            favouriteAdapter.submitList(it)
        }

        viewBinding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.list_item -> {
                    viewBinding.rvNews.adapter = adapter
                }

                R.id.favoris_item -> {
                    viewBinding.rvNews.adapter = favouriteAdapter
                }
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}