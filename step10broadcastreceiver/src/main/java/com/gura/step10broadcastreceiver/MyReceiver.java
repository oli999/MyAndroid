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
    //액티비티의 참조값을 담을 필드
    private MainActivity activity;
    //액티비티의 참조값을 필드에 저장하는 setter 메소드
    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    //특정 방송(비행기 모드가 on 또는 off 되었을때)이 수신되면 호출되는 메소드
    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isOn=intent.getBooleanExtra("state", false);
        if(isOn){
            Toast.makeText(context, "비행모드가 켜졌네?",
                    Toast.LENGTH_LONG).show();
            activity.updateUI("AirPlaneMode On");
        }else{
            Toast.makeText(context, "비행모드가 꺼졌네?",
                    Toast.LENGTH_LONG).show();
            activity.updateUI("AirPlaneMode Off");
        }

    }
}






