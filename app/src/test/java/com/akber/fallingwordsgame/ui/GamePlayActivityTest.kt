package com.akber.fallingwordsgame.ui

import com.akber.fallingwords.mock.Mocks
import org.junit.Before
import org.junit.Assert.*
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

class GamePlayActivityTest {
    @InjectMocks
    lateinit var activity: GamePlayActivity
    private var mocks = Mocks()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun wordInitialization() {
        val words = mocks.getDummyWordsList()
        activity.initializeWords(words)
        assertNotNull(activity.wordsList)
        assertEquals(activity.wordsList?.size, words.size)
    }

    @Test
    fun getNextWord() {
        val words = mocks.getDummyWordsList()
        activity.initializeWords(words)
        val nextWord = activity.getNextWord()
        assertNotNull(nextWord)
        assertEquals(activity.translationIndices.size, 0)
    }

    @Test
    fun getNextTranslation() {
        val words = mocks.getDummyWordsList()
        activity.initializeWords(words)
        val nextWord = activity.getNextTranslation()
        assertNotNull(nextWord)
        assertEquals(activity.translationIndices.size, 1)
    }

    @Test
    fun isCorrectVisible() {
        val words = mocks.getDummyWordsList()
        activity.initializeWords(words)
        val nextWord = activity.getNextWord()
        activity.currentWord = nextWord
        activity.currentTranslationOption = nextWord.word2
        assertTrue(activity.isCorrectVisible())
    }


    @Test
    fun isCorrectVisibleNegativeScenario() {
        val words = mocks.getDummyWordsList()
        activity.initializeWords(words)
        val nextWord = activity.getNextWord()
        activity.currentWord = nextWord
        activity.currentTranslationOption = "Wrong Translation abxdf"
        assertFalse(activity.isCorrectVisible())
    }

}