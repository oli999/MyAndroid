package test.kotlin01

enum class Color(val r:Int, val g:Int, val b:Int){
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255); // 함수와 구분하기 위해 ; 필요함

    //문자열을 리턴해주는 함수
    fun toHex():String{
        var result=Integer.toHexString(r)+
                Integer.toHexString(g)+
                Integer.toHexString(b);
        return result
    }
}

fun main(){
    var c1=Color.RED
    var c2=Color.GREEN
    var c3=Color.BLUE

    println("c1.r : ${c1.r} c1.g:${c1.g} c1.b:${c1.b}")
    println(c1.toHex())
}