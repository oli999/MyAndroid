package com.gura.step13service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //서비스 시작 버튼을 눌렀을때
    public void start(View v){
        //서비스를 시작 시킬 Intent 객체 생성
        Intent intent=new Intent(this, MyService.class);
        //Intent 객체를 이용해서 서비스를 시작 시킨다.
        startService(intent);
    }
    //서비스 종료 버튼을 눌렀을때
    public void end(View v){
        Intent intent=new Intent(this, MyService.class);
        //Intent 객체를 이용해서 서비스를 종료 시킨다.
        stopService(intent);
    }
}





