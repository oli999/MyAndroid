package com.gura.step10broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
    [ BroadcastReceiver 를 만드는 방법 ]

    - BroadcastReceiver 추상 클래스를 상속 받아서 만든다.
    - onReceive() 메소드를 오버라이딩 해서 방송이 수신 되었을때 원하는 작업을 한다.
    - AndroidManifest.xml 에 등록을 해야한다.
 */
public class MyReceiver extends BroadcastReceiver {
    //특정 방송이 수신되면 호출되는 메소드
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "방송이 수신되었네?",
                Toast.LENGTH_LONG).show();
    }
}






