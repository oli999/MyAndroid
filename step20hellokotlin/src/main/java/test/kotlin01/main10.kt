package test.kotlin01

fun main(){
    var num=1001

    var result = if(num%2==0) "짝수 입니다." else "홀수 입니다."

    println("$num 은 $result")

    var jumsu=85

    var result2=when{
        jumsu >= 90 -> "A 입니다."
        jumsu >= 80 -> "B 입니다."
        jumsu >= 70 -> "C 입니다."
        jumsu >= 60 -> "D 입니다."
        else -> "F 입니다."
    }
    println("$jumsu 를 학점으로 환산하면 $result2")

    println(calc2(65))
}

fun calc(jumsu:Int):String {
    return "xxx"
}

fun calc2(jumsu:Int) = when{
    jumsu >= 90 -> "A"
    jumsu >= 80 -> "B"
    jumsu >= 70 -> "C"
    jumsu >= 60 -> "D"
    else -> "F"
}




