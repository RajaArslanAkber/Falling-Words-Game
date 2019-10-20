package com.akber.fallingwordsgame.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.akber.fallingwordsgame.R
import com.akber.fallingwordsgame.model.Word

//opened for test cases
open class GamePlayActivity : AppCompatActivity() {

    val translationIndices: ArrayList<Int> = ArrayList()
    var currentWord: Word? = null
    var currentTranslationOption: String? = null
    val wordsList: ArrayList<Word>? = ArrayList()

    private lateinit var mViewModel: GamePlayViewModel
    private var toLearnEnglish: Boolean = true
    private var objectAnimator: ObjectAnimator? = null
    private lateinit var displayMetrics: DisplayMetrics
    private var unAnsweredCount: Int = 0
    private var correctCount: Int = 0
    private var wrongCount: Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    companion object {
        const val ANSWER_TIME_DURATION: Long = 10000 //seconds for floating translation
        const val GAME_LIFE: Int = 25 // max attempts, includes missing as well
        const val ARG_LEARN_ENG = "learn_english" // which language user wanted to learn
    }

}
