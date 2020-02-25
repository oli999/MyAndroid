package com.gura.step10broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //TextView 의 참조값을 담을 필드
    private TextView textView;
    //인텐트 필터에 전달할 Action 명을 상수로 미리 정의하기
    public static final String HUNGRY=
            "com.gura.step10broadcastreceiver.HUNGRY";
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

        HungryReceiver hr=new HungryReceiver();
        registerReceiver(hr, new IntentFilter(HUNGRY));

    }
    //방송 수신자 객체가 호출할 메소드
    public void updateUI(String msg){
        textView.setText(msg);
    }

    //버튼을 눌렀을때 호출되는 메소드
    public void btnClicked(View v){
        //인텐트 객체 생성하고
        Intent intent=new Intent();
        //액션명 지정
        intent.setAction(HUNGRY);
        //해당 인텐트 객체를 이용해서 방송하기
        sendBroadcast(intent);
    }
}







