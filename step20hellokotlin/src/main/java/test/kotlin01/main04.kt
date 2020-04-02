package test.kotlin01

//폭과 높이를 생성자의 인자로 전달 받는 Rect 클래스
class Rect(val width:Int, val height:Int){
    var isSquare:Boolean=false
        get() = width==height
}

fun main() {
    val rect1 = Rect(10, 20)
    var rect2 = Rect(20, 10)
    var rect3 = Rect(20, 20)
    println("rect1 의 정사각형 여부:${rect1.isSquare}")
    println("rect2 의 정사각형 여부:${rect2.isSquare}")
    println("rect3 의 정사각형 여부:${rect3.isSquare}")
}