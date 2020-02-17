package com.gura.step06fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
            implements MyFragment.MyFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //만일 activity_main.xml 에 정의된 fragment 객체의 참조값이
        //Activity 에서 필요 하다면?

        //프레그먼트 메니저 객체의 참조값을 얻어와서
        FragmentManager fm=getSupportFragmentManager();
        MyFragment myFragment=(MyFragment)fm.findFragmentById(R.id.myFragment);
    }
    //버튼을 눌렀을때 호출되는 메소드
    public void move(View v){
        Intent i=new Intent(this, SubActivity.class);
        startActivity(i);
    }
    //MyFragment 가 호출하는 메소드
    @Override
    public void showMessage(int count) {
        Toast.makeText(this, "현재카운트:"+count,
                Toast.LENGTH_LONG).show();
    }
}




