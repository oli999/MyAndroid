package com.example.step20hellokotlin

//클래스 정의하기
class MyClass

//클래스 정의하기
class YourClass{
    //맴버 함수
    fun printInfo(){
        println("YourClass 의 메소드(함수) 호출됨")
    }
}

//대표(primary) 생성자는 클래스명 우측에 선언한다
class OurClass1 constructor(){

}

class OurClass2(){ //consturctor 예약어 생략 가능

}

class OurClass3{ //인자로 전달 받을게 없으면 () 도 생략 가능

}

//생성자의 인자로 문자열을 전달 받는 생성자
class Car(name:String){
    //String type 을 담을수 있는 필드 선언
    var name:String
    //초기화 블럭 (객체가 생성되는 시점에 무언가 작업할수 있는 블럭)
    init {
        println("Car 클래스 init")
        //생성자의 인자로 전달 받은 문자열을 필드에 저장
        this.name=name
    }

    //함수
    fun drive(){
        println("$name 이(가) 달려요")
    }
}

// var 을 생성자의 인자에 선언하면 필드도 만들어 주고 전달된 값을 필드에 저장도 해준다.
// var 로 선언하면 getter, setter 가 모두 가능
class YourCar(var name:String){
    //맴버 함수 만들기
    fun drive(){
        println("$name 이(가) 달려요")
    }
}

class OurCar(){//primary 생성자
    //property 정의
    var name:String?=null
    //두번째 생성자 정의 하기
    constructor(name:String) : this() { // primary 생성자를 호출해야한다.
        //property(필드)에 값 대입하기
        this.name=name
    }
    fun drive(){
        println("$name 이(가) 달려요")
    }
}

fun main(){
    //MyClass 로 객체 생성해서 참조값을 my 라는 변수에 담기
    var my=MyClass()
    //YourClass 로 객체 생성해서 참조값을 your 라는 변수에 담기
    var your=YourClass()
    //printInfo() 함수 호출하기
    your.printInfo()

    var c1=Car("소나타")
    var info1 = c1.name; //필드(property) 참조
    c1.drive() //함수 호출

    var c2=YourCar("아반떼")
    var info2=c2.name  // property 참조
    println("info2 : $info2")
    c2.drive() // 함수 호출

    var c3=OurCar()
    var c4=OurCar("그랜저")

    c3.drive()
    c4.drive()
}

