package util.data.leancloud

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import model.Word
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import util.Net.getRequest
import java.util.*

class LeanCloudFinder{
    fun find(): Observable<ArrayList<Word>> {
        return Observable.create<ArrayList<Word>> {
            val map = hashMapOf<String,String>()
//        map["where"] = "reviewTime<" + (System.currentTimeMillis()-24*60*60*1000).toString()
            val s = getRequest("https://leancloud.cn:443/1.1/classes/word",map)
            println(s)
            val root = JsonParser().parse(s)
            root as JsonObject
            val results = root["results"]
            val t = object : TypeToken<List<Word>>(){}.type
            it.onNext(ArrayList(Gson().fromJson<List<Word>>(results,t)))
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}