package util.data.leancloud

import com.google.gson.Gson
import model.Word
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import util.Net.putJsonRequest

class LeanCloudUpdater{
    fun update(word: Word):Observable<String> {
        return Observable.create<String> {
            val s = Gson().toJson(word)
            it.onNext(putJsonRequest("https://leancloud.cn:443/1.1/classes/word/${word.objectId}",s))
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}