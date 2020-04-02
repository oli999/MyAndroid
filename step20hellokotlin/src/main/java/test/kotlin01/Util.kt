package test.kotlin01

class Util {
    //companion object (동반 객체)
    companion object{
        val version="1.0"
        @JvmField // java 에서 static 필드로 사용하고 싶을때
        val author="김구라"
        fun download(){
            println("다운로드 해요!")
        }
        @JvmStatic // java 에서 static 메소드로 사용하고 싶을때
        fun upload(){
            println("업로드 해요!")
        }
    }
}