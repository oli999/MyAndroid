package com.gura.step22notification

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //Intent 객체에 "msg" 라는 키값으로 담긴 문자열 읽어오기
        val msg=intent.getStringExtra("msg")
        //TextView 에 출력하기
        textView.setText(msg)
        //Intent 객체에 "id" 라는 키값으로 전달된 Int 값이 있는지 읽어와 본다.
        val id=intent.getIntExtra("id", -1)
        if(id != -1){ //알림을 수동으로 취소 해야 하는 경우
            //알림 메니저 객체의 참조값을 얻어와서
            val manager=getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
            //알림의 id 값을 전달해서 취소 시킨다.
            //manager.cancel(id)
            manager.cancelAll() //모든 알림 취소
        }
    }
}








