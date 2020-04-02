package test.kotlin01

import java.lang.NumberFormatException

fun main(){
    var str="1000"
    var str2="천"
    //try 식
    var result = try {
        Integer.parseInt(str2)
    }catch (ne : NumberFormatException){
        0
    }
    println(result + 10)
}