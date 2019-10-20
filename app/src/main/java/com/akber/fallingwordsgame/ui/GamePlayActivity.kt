package com.akber.fallingwordsgame.ui

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.akber.fallingwordsgame.R
import com.akber.fallingwordsgame.model.Word
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.counter_text.view.*
import kotlin.random.Random

//opened for test cases
open class GamePlayActivity : AppCompatActivity(), View.OnClickListener, Animator.AnimatorListener {

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


    override fun onPause() {
        super.onPause()
        objectAnimator?.isRunning.let {
            objectAnimator?.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        objectAnimator?.isRunning.let {
            objectAnimator?.resume()
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.correct_btn -> {
                if (isCorrectVisible()) {
                    updateCorrectCount()
                    setNewWord()
                } else {
                    updateWrongCount()
                    setNewTranslationWord()
                }
            }
            R.id.wrong_btn -> {
                if (isCorrectVisible()) {
                    updateWrongCount()
                    setNewWord()
                } else {
                    updateCorrectCount()
                    setNewTranslationWord()
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewModel = ViewModelProviders.of(this).get(GamePlayViewModel::class.java)
        correct_btn.setOnClickListener(this)
        wrong_btn.setOnClickListener(this)
        displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        initViewsState()
        initAnimation()

    }

    override fun onAnimationRepeat(animation: Animator?) {
    }

    override fun onAnimationCancel(animation: Animator?) {
    }

    override fun onAnimationStart(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animator?) {
        //reset the animation, get new words & update counts
        updateUnAnsweredCount()
        if (isCorrectVisible()) {
            setNewWord()
            return
        }
        setNewTranslationWord()

    }

    //get the word to place in center of screen
    fun getNextWord(): Word {
        translationIndices.clear()
        return getRandomUniqueWord(false)
    }

    //check if the floating translation is correct?
    fun isCorrectVisible(): Boolean {
        return if (toLearnEnglish) {
            currentWord?.word2.equals(currentTranslationOption, ignoreCase = true)
        } else {
            currentWord?.word1.equals(currentTranslationOption, ignoreCase = true)
        }
    }

    //initialize the words to begin
    fun initializeWords(words: ArrayList<Word>) {
        wordsList?.clear()
        wordsList?.addAll(words)
    }

    //update timer text
    private fun updateCounterText(counter: String?) {
        counter?.let {
            timer_value.text = it
        }
    }

    //get the next unique word for game, randomly picked from data set
    //logic limited right now
    private fun getRandomUniqueWord(isForTranslation: Boolean): Word {
        val random = Random(System.currentTimeMillis())
        var index: Int
        do {
            index = random.nextInt(0, 15)
        } while (translationIndices.contains(index))

        if (isForTranslation) {
            translationIndices.add(index)
        }
        return wordsList!![index]
    }


    //get the next option for choosing
    fun getNextTranslation(): Word {
        return getRandomUniqueWord(true)
    }

    //set the next word to begin next choices, also having logic of which learning you choose
    private fun setNewWord() {
        currentWord = getNextWord()
        if (toLearnEnglish) {
            word.text = currentWord?.word1
        } else {
            word.text = currentWord?.word2
        }
        setNewTranslationWord()
    }

    //set the next word's translation, also having logic of which learning you choose
    private fun setNewTranslationWord() {
        if(toLearnEnglish) {
            currentTranslationOption = getNextTranslation().word2
        } else {
            currentTranslationOption = getNextTranslation().word1
        }
        translation_text.text = currentTranslationOption
        animateTranslationText()
    }

    //animate the new translation from top to bottom
    private fun animateTranslationText() {
        startAnimation()
    }

    //starting animation and handle timer
    private fun startAnimation() {
        stopAnimation()
        objectAnimator?.start()
    }

    //stopping animation on textView
    private fun stopAnimation() {
        objectAnimator?.isRunning?.let {
            translation_text.clearAnimation()
        }
    }

    //updating count
    private fun updateCorrectCount() {
        correctCount += 1
        success_counter_layout.counter_value.text = correctCount.toString()
        checkGameLife()
    }

    //updating count
    private fun updateWrongCount() {
        wrongCount += 1
        failure_counter_layout.counter_value.text = wrongCount.toString()
        checkGameLife()
    }

    //updating count
    private fun updateUnAnsweredCount() {
        unAnsweredCount += 1
        unAnswered_counter_layout.counter_value.text = unAnsweredCount.toString()
        checkGameLife()
    }

    //checking attempts to end game
    private fun checkGameLife() {
        if (unAnsweredCount + correctCount + wrongCount == GAME_LIFE) {
            gameOver()
        }
    }

    private fun gameOver() {
        startResultActivity()
        finish()
    }
    private fun startResultActivity() {

    }

    //setting counter titles
    private fun initViewsState() {
        success_counter_layout.counter_text.text = getString(R.string.correctTitle)
        failure_counter_layout.counter_text.text = getString(R.string.wrongTitle)
        unAnswered_counter_layout.counter_text.text = getString(R.string.missedTitle)

    }

    //initialize floating animation first time
    private fun initAnimation() {

        objectAnimator = ObjectAnimator.ofFloat(
            translation_text,
            "translationY",
            0f,
            displayMetrics.heightPixels.toFloat()
        )

        objectAnimator?.duration = ANSWER_TIME_DURATION
        objectAnimator?.addListener(this@GamePlayActivity)
    }

    companion object {
        const val ANSWER_TIME_DURATION: Long = 10000 //seconds for floating translation
        const val GAME_LIFE: Int = 25 // max attempts, includes missing as well
        const val ARG_LEARN_ENG = "learn_english" // which language user wanted to learn
    }

}
