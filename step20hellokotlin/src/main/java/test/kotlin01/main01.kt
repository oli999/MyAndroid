package test.kotlin01


//실행순서가 시작되는 main 함수
fun main(){
    // kotlin  에서  java class 사용가능?
    val mem1=MemberDto()
    //java class 를 import 해서 setter 메소드 사용하기
    mem1.num=1
    mem1.name="김구라"
    mem1.addr="노량진"
    //java class 를 import 해서 getter 메소드 사용하기
    var a=mem1.num
    var b=mem1.name
    var c=mem1.addr

    val mem2=MemberDto(2, "해골", "행신동")

}






