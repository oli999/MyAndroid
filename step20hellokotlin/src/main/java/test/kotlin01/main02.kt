package test.kotlin01



fun main(){
    //import 된 java 클래스의 클래스 type
    val a:Class<MemberDto> = MemberDto::class.java
    val b = MemberDto::class.java
}