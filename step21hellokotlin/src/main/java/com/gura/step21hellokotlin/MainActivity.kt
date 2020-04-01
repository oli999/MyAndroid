package com.gura.step21hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // null 로 property 를 초기화 하기
    //var inputMsg:EditText?=null

    // 나중에 초기화 하겠다는 keyword  lateinit
    lateinit var inputMsg:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //EditText 객체의 참조값
        inputMsg=findViewById<EditText>(R.id.inputMsg)
        //Button 객체의 참조값
        val addBtn:Button=findViewById(R.id.addBtn)
        //Button 에 리스너 등록
        addBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        //입력한 문자열 읽어오기
        val msg=inputMsg.text.toString()

        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}








