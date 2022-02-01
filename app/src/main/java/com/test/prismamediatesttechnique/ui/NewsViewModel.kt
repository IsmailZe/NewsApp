package com.test.prismamediatesttechnique.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.prismamediatesttechnique.data.models.News
import com.test.prismamediatesttechnique.data.models.State
import com.test.prismamediatesttechnique.domain.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<State<List<News>>>()
    val newsLiveData: LiveData<State<List<News>>> get() = _newsLiveData

    private val _favouriteNewsLiveData = MutableLiveData<ArrayList<News>>()
    val favouriteNewsLiveData: LiveData<ArrayList<News>> get() = _favouriteNewsLiveData

    suspend fun getNews() {
        viewModelScope.launch {
            try {
                _newsLiveData.value = State.LoadingState
                val news = newsUseCase.getNews().data.items
                _newsLiveData.value = State.DataState(news)
            } catch (e: Exception) {
                _newsLiveData.value = State.ErrorState(e)
            }
        }
    }

    fun refreshFavourites(news: News) {
        val favouriteNews  = _favouriteNewsLiveData.value?: arrayListOf()
        if(news.favourite){
            favouriteNews.add(news)
        }else {
            favouriteNews.remove(news)
        }
        _favouriteNewsLiveData.postValue(favouriteNews)
    }
}