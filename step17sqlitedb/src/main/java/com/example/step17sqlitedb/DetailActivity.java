package com.example.step17sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {
    private DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Intent 에 전달된 TodoDto 객체 얻어내기
        TodoDto dto=(TodoDto)getIntent().getSerializableExtra("dto");
        helper=new DBHelper(this, "MyDB.sqlite",
                null, 1);
    }
}










