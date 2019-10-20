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

//opened for test cases
open class GamePlayActivity : AppCompatActivity(),View.OnClickListener, Animator.AnimatorListener {

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


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.correct_btn -> {

            }
            R.id.wrong_btn -> {

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
