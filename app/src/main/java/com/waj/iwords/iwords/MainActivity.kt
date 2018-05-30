package com.waj.iwords.iwords

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.waj.iwords.iwords.util.AudioPlayer
import kotlinx.android.synthetic.main.activity_main.*
import model.Word
import rx.functions.Action1
import util.Abhs
import util.data.leancloud.LeanCloudFinder
import util.data.leancloud.LeanCloudUpdater

class MainActivity : Activity() {
    var index:Int = 0
    lateinit var ws:List<Word>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toast("请稍候，正在获取最新数据")
        LeanCloudFinder().find().subscribe { list ->
            val ct = System.currentTimeMillis()
            val rl = list.filter { it.reviewTime< ct }
            toast("已获取待复习的词")
            if (rl.isEmpty()) toast("没有需要复习的单词")
            ws = rl
            displayWord()
        }

        setViewListener()
    }

    private fun setViewListener() {
        tvPlay.setOnClickListener{
            AudioPlayer.play(ws[index].q)
        }
        tvExplainBtn.setOnClickListener {
            tvExplain.text = ws[index].r
        }
        tvNext.setOnClickListener {
            if (index+1>ws.size) {
                toast("已是最后一个生词了")
            }
            uploadRemInfo()
            index += 1
            displayWord()
        }
    }

    private fun uploadRemInfo() {
        val it = ws[index]
        it.reviewTime = Abhs.nextTime(it.reviewTime, it.reviewedCount)
        it.reviewedCount = it.reviewedCount + 1
        LeanCloudUpdater().update(it).subscribe {
            println(it)
        }
    }

    private fun displayWord() {
        val w = ws[index]
        tvWord.text = w.q
        tvUs.text = w.usp
        tvUk.text = w.ukp
        tvProgress.text = "${index+1}/${ws.size}"
        tvExplain.text = ""
    }

    fun toast(msg:String){
        Toast.makeText(this,msg,LENGTH_SHORT).show()
    }
}
