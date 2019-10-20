package com.akber.fallingwordsgame.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.akber.fallingwordsgame.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when (v?.id) {// user has choice which language he need to learn
            R.id.eng_btn -> {
                startGame(true)
            }
            R.id.spa_btn -> {
                startGame(false)
            }
        }
    }

    private fun startGame(toLearnSpanish: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        spa_btn.setOnClickListener(this)
        eng_btn.setOnClickListener(this)
    }
}