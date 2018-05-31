package com.waj.iwords.iwords.util

import android.media.MediaPlayer

/**
 * Created by waj on 18-5-30.
 */
object AudioPlayer{
    fun play(word: String) {
        val urlStr = "http://dict.youdao.com/speech?audio=$word"
        val mp = MediaPlayer()
        mp.setDataSource(urlStr)
        mp.prepare()
        mp.setOnPreparedListener {
            mp.start()
        }
        mp.setOnCompletionListener {
            mp.reset()
        }
    }
}