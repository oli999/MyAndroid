package com.gura.step22notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //현재 알림의 아이디
    var currentId=0
    //체널의 아이디를 겹치지 않게 유일하게 구성하기
    val CHANNEL_ID="com.gura.step22notification.ALERT_CHANNEL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //버튼에 리스너 등록하기
        notiBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {//눌러진 버튼의 참조값이 View type 으로 전달된다.
        when(v?.id){
            R.id.notiBtn -> {
                //입력한 문자열을 읽어와서
                val msg=inputMsg.text.toString()
                //알림에 띄운다.
                makeAutoCancelNoti(msg)
            }
        }
    }
    //인자로 전달되는 문자열을 알림에 띄우는 함수
    fun makeAutoCancelNoti(msg:String){
        //이 앱의 알림 체널 만들기
        createNotificationChannel()
        //알림을 클릭했을때 실행할 Activity 정보를 가지고 있는 Intent 객체
        val intent = Intent(this, DetailActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("msg", msg)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra("msg", msg)

        //Intent 객체를 인텐트 전달자 객체에 담는다.
        val pendingIntent: PendingIntent =
                PendingIntent.getActivity(this, 0, intent, 0)

        //NotificationCompat.Builer 객체 생성
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("오빠 나야~")
                .setContentText(msg)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        //아이디값 1 증가 시키기
        currentId++

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(currentId, builder.build())
        }
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "kim"
            val descriptionText = "test!"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}






