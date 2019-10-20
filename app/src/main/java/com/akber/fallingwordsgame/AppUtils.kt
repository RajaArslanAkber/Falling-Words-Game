package com.akber.fallingwordsgame

import android.content.Context
import java.io.IOException

object AppUtils {
    fun loadJSONFromAsset(context: Context, fileName: String): String? {
        var json: String? = null
        try {
            val `is` = context.assets.open(fileName)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
}
