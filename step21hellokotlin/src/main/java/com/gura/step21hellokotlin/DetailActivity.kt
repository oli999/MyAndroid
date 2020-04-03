package com.gura.step21hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        //Intent 객체에 담긴 문자열 읽어오기
        val msg=intent.getStringExtra("item")
        //TextView 에 출력하기
        textView.setText(msg)
    }
}









