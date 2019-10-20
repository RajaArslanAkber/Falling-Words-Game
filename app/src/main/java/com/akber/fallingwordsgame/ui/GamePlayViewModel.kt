package com.akber.fallingwordsgame.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.akber.fallingwordsgame.model.Word
import com.akber.fallingwordsgame.repositories.WordRepository

class GamePlayViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: WordRepository? = WordRepository(application).getInstance(application)

    //will be called from UI thread
    fun getWordsLiveData(): MutableLiveData<ArrayList<Word>>? {
        return repository?.getWordsLiveData()
    }

    //will be called from UI thread
    fun fetchWords() {
        repository?.fetchWords()
    }
}