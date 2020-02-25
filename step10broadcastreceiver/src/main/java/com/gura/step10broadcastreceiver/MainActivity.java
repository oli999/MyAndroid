package com.gura.step10broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //TextView 의 참조값을 담을 필드
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView 의 참조값을 필드에 저장
        textView=findViewById(R.id.textView);

        //방송 수신자 객체 생성해서
        MyReceiver mr=new MyReceiver();
        //동작이 가능하도록 등록하기
        registerReceiver(mr,
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
        //방송 수신자 객체에 MainActivity 의 참조값 전달하기
        mr.setActivity(this);
    }
    //방송 수신자 객체가 호출할 메소드
    public void updateUI(String msg){
        textView.setText(msg);
    }
}







