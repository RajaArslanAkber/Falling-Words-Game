package com.akber.fallingwordsgame.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.akber.fallingwordsgame.R
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity(), View.OnClickListener {

    private var mCorrectCount: String? = ""
    private var mTotalCount: String? = ""

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.retry_btn -> {
                finish()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        intent?.let {
            mTotalCount = it.getStringExtra(ARG_TOTAL_COUNT)
            mCorrectCount = it.getStringExtra(ARG_CORRECT_COUNT)
        }
        score_value.text = "Score $mCorrectCount / $mTotalCount"

        retry_btn.setOnClickListener(this)
    }

    companion object {
        const val ARG_TOTAL_COUNT = "total_count"
        const val ARG_CORRECT_COUNT = "correct_count"
    }
}