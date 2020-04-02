package test.kotlin01

class Friend{
    //property
    var num:Int=0
    var name:String?=null
        set(value) {
            field=value+"님"
        }
    var phone:String?=null
        get() {
            return field ?: "전화번호 없음"
        }

    fun showInfo(){
        println("num:${num} name:${name} addr:${phone}")
    }
}

fun main() {
    val f1=Friend()
    f1.name="김구라"
    f1.showInfo()
}





