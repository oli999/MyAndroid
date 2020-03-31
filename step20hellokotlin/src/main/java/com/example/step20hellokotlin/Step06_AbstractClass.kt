package com.example.step20hellokotlin

//추상 클래스
abstract class Weapon{
    fun move(){
        println("이동 합니다.")
    }
    //추상 함수 ( 모양만 정의된 함수)
    abstract fun attack()
}

//추상 클래스를 상속 받은 클래스
class MyWeapon:Weapon(){
    //추상 함수 오버라이드
    override fun attack() {
        println("지상 공격을 해요")
    }
}

fun main(){
    var w1=MyWeapon()
    w1.move()
    w1.attack()
    //annonymous inner type
    var w2=object:Weapon(){
        override fun attack() {
            println("공중 공격을 해요")
        }
    }
    w2.move()
    w2.attack()
}






