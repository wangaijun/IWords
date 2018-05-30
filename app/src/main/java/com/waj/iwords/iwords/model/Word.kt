package model

data class Word(val q:String, val r:String, val usp:String, val ukp:String, var reviewTime: Long, var reviewedCount:Int){
    override fun toString(): String {
        return "$q\n$r\n$usp\n$ukp"
    }
    var objectId:String?=null
}