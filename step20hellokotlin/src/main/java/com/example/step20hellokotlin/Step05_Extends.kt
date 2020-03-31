package com.example.step20hellokotlin

// kotlin 에서는 default 로 final 클래스 이다.
// 상속을 받을수 있게 하려면 open 이라는 예약으롤 붙여 주어야 한다.
open class Phone{
    fun call(){
        println("전화를 걸어요")
    }
}

//Phone 클래스를 상속 받기
open class HandPhone : Phone(){ //생성자가 있으면 호출하는 표현식을 써야한다.
    fun mobileCall(){
        println("이동중에 전화를 걸어요")
    }
    //함수도 default 로는 final 이다
    //override 가능하게 하려면 open 이라는 예약어가 필요하다
    open fun takePicture(){
        println("30만 화소의 사진을 찍어요")
    }
}

class SmartPhone : HandPhone(){
    fun doInternet(){
        println("인터냇을 해요")
    }
    //open 된 함수를 override 할수 있다
    override fun takePicture() {
        //super.takePicture() 부모 메소드 호출
        println("1000만 화소의 사진을 찍어요")
    }
}

fun main(){
    var p1=Phone()
    var p2=HandPhone()
    var p3=SmartPhone()
    println("- Phone -")
    p1.call()
    println("- HandPhone -")
    p2.call()
    p2.mobileCall()
    p2.takePicture()
    println("- SmartPhone -")
    p3.call()
    p3.mobileCall()
    p3.doInternet()
    p3.takePicture()
}

