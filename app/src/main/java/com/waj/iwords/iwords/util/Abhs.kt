package util

object Abhs{
    fun nextTime(thisTime:Long, thisReviewCount:Int):Long{
        return thisTime + when(thisReviewCount){
            0-> first
            1-> second
            2-> third
            3-> forth
            4-> fifth
            5-> sixth
            6-> seventh
            7-> eighth
            else-> other
        }
    }

    val first = 5*60*1000
    val second = 30*60*1000
    val third = 12*60*60*1000
    val forth = 1*24*60*60*1000
    val fifth = 2*24*60*60*1000
    val sixth = 4*24*60*60*1000
    val seventh = 7*24*60*60*1000
    val eighth = 15*24*60*60*1000
    val other = 60*24*60*60*1000
}