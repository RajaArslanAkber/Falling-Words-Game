package com.akber.fallingwordsgame.model

import com.google.gson.annotations.SerializedName

data class Word(@SerializedName("text_eng") val word1: String, @SerializedName("text_spa") val word2: String)