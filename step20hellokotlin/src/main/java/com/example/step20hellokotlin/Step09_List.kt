package com.example.step20hellokotlin

// data class 정의하기 (DTO)
data class Member(var num:Int, var name:String)

fun main(){
    //수정 불가능한 List
    var nums:List<Int> = listOf(10, 20, 30, 40, 50)
    var names:List<String> = listOf("kim","lee","park")

    // data class 로 객체를 생성하고
    var mem1=Member(1, "김구라")
    var mem2=Member(2, "해골")
    var mem3=Member(3, "원숭이")
    // 배열에 참조값을 담아 놓기
    var members:List<Member> = listOf(mem1, mem2, mem3)


    var a=members[0].num //1
    var b=members[0].name //"김구라"

    var c=members.get(0).num //1
    var d=members.get(0).name //"김구라"

    //배열 자체의 데이터는 수정 불가하다 (immutable List 이기 때문에)
    //members[0]=Member(4, "주뎅이")

    //배열안에 있는 객체의 필드 수정은 가능하다 (var 로 객체의 필드가 선언되어 있다면)
    members[0].name="이정호"
}






