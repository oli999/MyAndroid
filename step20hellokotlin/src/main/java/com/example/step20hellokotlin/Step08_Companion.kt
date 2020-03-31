package com.example.step20hellokotlin

class MyUtil{
    //클래스명에 . 찍어서 사용할수 있는 자원 만들기
    companion object{
        val color="RED"
        fun write(){
            println(color+" 색으로 필기를 해요!")
        }
    }
}

class MyDao private constructor(){ //외부에서 객체 생성하지 못하도록
    companion object{
        private var dao:MyDao?=null
        fun getInstance():MyDao?{
            if(dao==null){
                dao= MyDao()
            }
            return dao
        }
    }

    fun insert(){}
    fun update(){}
}

// single ton 으로 사용할수 있는 Dao
class YourDao private constructor(){
    companion object{
        private val dao=YourDao()
        fun getInstance():YourDao{
            return dao
        }
    }
    fun insert(){}
    fun update(){}
}


fun main(){
    MyUtil.write()  //static 메소드 호출
    var a=MyUtil.color //static 필드 참조
    println("main 함수가 종료 됩니다.")

    MyDao.getInstance()?.insert()
    MyDao.getInstance()?.update()

    YourDao.getInstance().insert()
    YourDao.getInstance().update()
}