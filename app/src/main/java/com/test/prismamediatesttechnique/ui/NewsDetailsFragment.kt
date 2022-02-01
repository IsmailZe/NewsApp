package com.test.prismamediatesttechnique.ui

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.test.prismamediatesttechnique.R
import com.test.prismamediatesttechnique.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {

    private var _viewBinding: FragmentNewsDetailsBinding? = null
    private val viewBinding: FragmentNewsDetailsBinding get() = _viewBinding!!
    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val news = args.news
        with(viewBinding) {
            tvTitle.text = news.title
            tvDate.text = news.dateIndexed
            tvBody.text =  Html.fromHtml(news.body, Html.FROM_HTML_MODE_COMPACT)
            ivFavourite.setImageResource(if (news.favourite) R.drawable.ic_star_full else R.drawable.ic_star_empty)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}