package com.gura.step12sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
                            implements View.OnClickListener{
    //필요한 필드
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //EditText 참조값 얻어와서 필드에 저장하기
        editText=findViewById(R.id.editText);
        //Button 의 참조값 얻어와서 리스너 등록하기
        Button saveBtn=findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        SharedPreferences pref=
                getSharedPreferences("info", MODE_PRIVATE);
        // .getString( key 값, default 값)
        String myName=pref.getString("myName", "empty");
        if(myName.equals("empty")){
            return;
        }
        //저장된 이름을 editText 에 출력하기
        editText.setText(myName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //메뉴 전개자 객체를 이용해서 res/menu/menu_main.xml 문서를 전개 해서 메뉴를 구성한다.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //선택된 메뉴 아이템의 아이디값을 읽어온다.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }else if(id == R.id.finish){
            //액티비티 종료 시키기
            finish();
        }else if(id == R.id.start){

        }
        return true;
    }

    @Override
    public void onClick(View view) {
        //입력한 문자열 읽어오기
        String inputName=editText.getText().toString();
        //SharedPreferences 를 활용해서 저장하기

        SharedPreferences pref=
                getSharedPreferences("info", MODE_PRIVATE);
        //Editor 객체의 참조값을 얻어와서
        SharedPreferences.Editor editor=pref.edit();
        //myName 이라는 키값으로 문자열 저장하기
        editor.putString("myName", inputName);
        editor.commit(); //실제 저장되는 시점
        //알림 띄우기
        new AlertDialog.Builder(this)
                .setMessage("저장 했습니다.")
                .setNeutralButton("확인", null)
                .show();

    }
}









