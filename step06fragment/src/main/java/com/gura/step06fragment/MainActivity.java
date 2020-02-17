package com.gura.step06fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //버튼을 눌렀을때 호출되는 메소드
    public void move(View v){
        Intent i=new Intent(this, SubActivity.class);
        startActivity(i);
    }
    //프레그 먼트가 호출할 메소드
    public void showMessage(int count){
        Toast.makeText(this, "현재 카운트:"+count,
                Toast.LENGTH_LONG).show();
    }
}




