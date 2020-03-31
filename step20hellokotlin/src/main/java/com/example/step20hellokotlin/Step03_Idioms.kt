package com.example.step20hellokotlin

//MemberDto 클래스 정의하기
data class MemberDto(var num:Int,var name:String, var addr:String)

fun foo(num:Int=0, name:String="이름"){
    println("num:$num name:$name")
}

fun main(){

    var sql="SELECT *" +
            "FROM MEMBER" +
            "WHERE NUM=1"

    var sql2="""
        SELECT *
        FROM MEMBER
        WHERE NUM=1
    """.trimIndent()

    println(sql2)

    //변경불가(immutable)한 Map
    var info= mapOf<String, Any>("num" to 1,"name" to "김구라",
            "isMan" to true)
    //Map 의 데이터 참조
    var myNum:Int = info["num"] as Int  // as 연산자를 이용해서 casting
    var myName:String = info["name"] as String
    var myIsMan:Boolean = info["isMan"] as Boolean

    //숫자 배열
    var nums= listOf(-20, -10, 0, 10, 20)

    for(i in 0 until nums.size){
        println("$i 번방 : ${nums[i]}")
    }

    // item 중에서 0보다 큰 값만 추려서 새로운 배열 얻어내기
    //var result=nums.filter { x -> x > 0 }
    var result=nums.filter { it > 0  }
    println(result.toString())


    foo(1, "김구라")
    foo()
    foo(2)
    foo(name="해골")
    foo(name="원숭이", num=3)

    //MemberDto 에 한명의 회원 정보를 저장
    var mem1=MemberDto(1, "김구라", "노량진")
    //내부 적으로 getter 메소드가 사용됨
    var num=mem1.num
    var name=mem1.name
    var addr=mem1.addr
    //내부적으로 setter  메소드가 사용됨
    mem1.num=2
    mem1.name="이정호"
    mem1.addr="독산동"
    // MemberDto 객체를 Any type 변수에 담기
    var whoami:Any = mem1

    when(whoami){
        is String -> println("String type 이네?")
        is MemberDto -> println("MemberDto type 이네?")
        else -> println("무슨 type 인지 모르겠다")
    }
}
