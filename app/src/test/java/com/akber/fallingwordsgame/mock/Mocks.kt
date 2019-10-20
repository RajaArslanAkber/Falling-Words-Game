package com.akber.fallingwords.mock

import com.akber.fallingwordsgame.model.Word


class Mocks{

    fun getDummyWordsList():ArrayList<Word>{
        val wordsList: ArrayList<Word>? = ArrayList()

        var word: Word = Word("apple", "saib")
        wordsList?.add(word)
        word = Word("mango", "aaam")
        wordsList?.add(word)
        word = Word("banana", "kela")
        wordsList?.add(word)
        word = Word("grapes", "angoor")
        wordsList?.add(word)
        word = Word("dates", "khajoor")
        wordsList?.add(word)
        word = Word("monday", "sumwaar")
        wordsList?.add(word)
        word = Word("wednesday", "budh")
        wordsList?.add(word)
        word = Word("friday", "jumma")
        wordsList?.add(word)
        word = Word("saturday", "hafta")
        wordsList?.add(word)
        word = Word("sunday", "itwar")
        wordsList?.add(word)

        return wordsList!!
    }

}