package com.gura.step13service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.Nullable;

/*
    [ UI 없이 백그라운드에서 동작할수 있는 Service ]

    1. Service 추상클래스를 상속 받는다.
    2. onBind() 메소드 오버라이딩
    3. 추가로 필요한 메소드를 오버라이딩해서 작업한다.

 */
public class MyService extends Service {
    //특정 Activity 와 연결이 되었을때 호출되는 메소드 (이 예제에서는 할 작업이 없다)
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    //초기화 할 작업을 하는 메소드
    @Override
    public void onCreate() {
        super.onCreate();
    }
    //서비스에서 수행해야할 main 작업을 하는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //핸들러에 메세지 보내기
        handler.sendEmptyMessage(0);
        return super.onStartCommand(intent, flags, startId);
    }
    //서비스가 종료 될때 마무리 작업을 하는 메소드
    @Override
    public void onDestroy() {
        super.onDestroy();
        //핸들러 동작 하지 않도록
        handler.removeMessages(0);
    }
    //카운트값을 저장할 필드
    int count=0;

    //핸들러
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            count++;
            Toast.makeText(getApplicationContext(),
                    count+" 번 호출", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(0, 5000);
        }
    };
}

















