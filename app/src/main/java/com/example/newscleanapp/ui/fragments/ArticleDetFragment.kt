package com.example.newscleanapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newscleanapp.databinding.FragmentArticleDetBinding
import com.example.newscleanapp.R
import com.example.newscleanapp.databinding.FragmentArticlesBinding
import dagger.hilt.android.AndroidEntryPoint


class ArticleDetFragment : Fragment() {

    private lateinit var fgDetBinding: FragmentArticleDetBinding
    lateinit var articleTitle: String
    lateinit var articleDes: String
    lateinit var articleImg: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fgDetBinding = FragmentArticleDetBinding.inflate(inflater, container, false)
        return fgDetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: ArticleDetFragmentArgs by navArgs()
        articleTitle = args.articleTitle
        articleDes = args.articleDes
        articleImg = args.articleImg
        fgDetBinding.articleNameTv.text = articleTitle
        fgDetBinding.articleDesTv.text = articleDes
        Glide.with(requireContext()).load(articleImg)
            .into(fgDetBinding.articleIv)
    }

}