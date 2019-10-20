package com.akber.fallingwordsgame.repositories

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.akber.fallingwordsgame.AppUtils
import com.akber.fallingwordsgame.constants.AppConstants
import com.akber.fallingwordsgame.model.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WordRepository(private var application: Application) {


    private val lock = Any()
    private var sInstance: WordRepository? = null
    private var mWords: MutableLiveData<ArrayList<Word>> = MutableLiveData()

    @Synchronized
    fun getInstance(application: Application): WordRepository? {
        if (sInstance == null) {//avoid concurrency issues
            synchronized(lock) {
                sInstance = WordRepository(application)
            }
        }
        return sInstance
    }


    fun getWordsLiveData(): MutableLiveData<ArrayList<Word>> {
        return mWords // observers will be applied on it
    }

    fun fetchWords() { //fetching words from local assets, but can be fetched from any data source and postValue
        val jsonWords =
            AppUtils.loadJSONFromAsset(application.applicationContext, AppConstants.FILE_NAME)
        jsonWords?.let {
            val gson = Gson()
            val listType = object : TypeToken<ArrayList<Word>>() {
            }.type
            val words: ArrayList<Word> = gson.fromJson<ArrayList<Word>>(it, listType)
            mWords.postValue(words)
        }
    }

}