package com.example.newscleanapp.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.Article
import com.example.newscleanapp.adapter.ArticlesAdapter
import com.example.newscleanapp.databinding.FragmentArticlesBinding
import com.example.newscleanapp.viewmodel.ArticlesViewModel
import com.example.newscleanapp.state.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticlesFragment : Fragment() {
    private lateinit var fgBinding: FragmentArticlesBinding
    private var articleAdapter: ArticlesAdapter? = null
    private val viewModel: ArticlesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fgBinding = FragmentArticlesBinding.inflate(inflater, container, false)
        return fgBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fgBinding.articlesRv
        articleAdapter = ArticlesAdapter(onClicked)
        fgBinding.apply {
            articlesRv.apply {
                adapter = articleAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        lifecycleScope.launch {
            viewModel.articles.observe(viewLifecycleOwner) {
                articleAdapter!!.submitList(it.data)

                fgBinding.apply {
                    progressBar.isVisible =
                        it is Resource.Loading<*> && it.data!!.isNullOrEmpty()
                    tvError.isVisible =
                        it is Resource.Error<*> && it.data!!.isNullOrEmpty()
                    tvError.text = it.error?.localizedMessage
                }

            }

        }
    }

    private val onClicked = object : ArticlesAdapter.OnItemClickListener {
        override fun onClicked(article: Article) {
            val articleTitle = article.title
            val articleDes = article.description
            val articleImg = article.urlToImage
            val action1 = ArticlesFragmentDirections.actionArticlesFragmentToArticleDetFragment(
                articleTitle, articleDes, articleImg
            )
            findNavController().navigate(action1)
        }
    }
}



