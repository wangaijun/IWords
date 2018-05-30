package com.waj.iwords.iwords.util

import android.media.MediaPlayer

/**
 * Created by waj on 18-5-30.
 */
object AudioPlayer{
    var mp:MediaPlayer = MediaPlayer()
    fun play(word: String) {
        val urlStr = "http://dict.youdao.com/speech?audio=$word"
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