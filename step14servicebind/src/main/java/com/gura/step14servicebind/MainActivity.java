package com.gura.step14servicebind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //필요한 맴버필드 정의하기
    MessengerService mService;
    //서비스에 연결되었는지 여부
    boolean mServiceConnected=false;
    EditText console;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        console = (EditText)findViewById(R.id.editText);
        //서비스를 시작 시킨다.
        Intent intent=new Intent(this, MessengerService.class);
        startService(intent);
    }
    //서비스 종료 버튼을 눌렀을때
    public void end(View v){
        if(mServiceConnected){//서비스에 바인딩 된 상태라면
            //바인딩을 해제해준다.
            unbindService(mConn);
            mServiceConnected=false;
        }
        Intent intent=new Intent(this, MessengerService.class);
        stopService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //서비스 바인딩하기
        Intent intent=new Intent(this, MessengerService.class);
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        if(mServiceConnected){//서비스에 바인딩 된 상태라면
            //바인딩을 해제해준다.
            unbindService(mConn);
            mServiceConnected=false;
        }
        super.onStop();
    }
    //서비스로 부터 문자열을 전달받을 메소드
    public void setMsg(final String msg){

        //UI 스레드에서 동작할수 있도록 한다.
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //콘솔에 출력하기
                console.append(msg+"\n");
            }
        });
    }
    //서비스 연결객체
    ServiceConnection mConn=new ServiceConnection() {
        //서비스와 연결되었을때 호출되는 메소드
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //서비스의 onBind() 메소드에서 리턴해주는 IBinder 객체가 들어온다.
            MessengerService.LocalBinder
                    localBinder = (MessengerService.LocalBinder)service;
            //MessengerService 의 참조값을 맴버필드에 저장한다.
            mService=localBinder.getService();
            //서비스에 액티비티의 참조값을 전달한다.
            localBinder.setActivity(MainActivity.this);
            //서비스와 연결되었다고 표시한다.
            mServiceConnected=true;

        }
        //서비스와 연결 해제 되었을때 호출되는 메소드
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceConnected=false;
            mService=null;
        }
    };
}
