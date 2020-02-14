package com.gura.step02activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
                            implements View.OnClickListener{
    // View.OnClickListener 인터페이스 type 을 필드에 가지고 있기
    View.OnClickListener listener=new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            //1. EditText 에 입력한 문자열을 읽어온다.
            EditText inputMsg=findViewById(R.id.inputMsg); // id="@+id/inputMsg"
            String msg=inputMsg.getText().toString();
            //2. Toast 메세지에 문자열을 띄우기
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //전송2 버튼의 참조값 얻어오기
        Button sendBtn=findViewById(R.id.sendBtn);
        //버튼에 클릭 리스너 등록하기
        sendBtn.setOnClickListener(this);

        //전송3 버튼의 참조값 얻어오기
        Button sendBtn2=findViewById(R.id.sendBtn2);
        sendBtn2.setOnClickListener(listener);
    }
    //전송 버튼을 클릭하면 호출되는 함수
    public void sendClicked(View v){
        //1. EditText 에 입력한 문자열을 읽어온다.
        EditText inputMsg=findViewById(R.id.inputMsg); // id="@+id/inputMsg"
        String msg=inputMsg.getText().toString();
        //2. Toast 메세지에 문자열을 띄우기
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    //리스너를 등록한 버튼을 클릭하면 호출되는 메소드
    @Override
    public void onClick(View view) {
        //1. EditText 에 입력한 문자열을 읽어온다.
        EditText inputMsg=findViewById(R.id.inputMsg); // id="@+id/inputMsg"
        String msg=inputMsg.getText().toString();
        //2. Toast 메세지에 문자열을 띄우기
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    //다음 예제로 이동하기 버튼을 눌렀을때 호출되는 메소드
    public void move(View v){
        //액티비티를 이동하기 위한 Intent(의도) 객체를 생성한다.
        Intent intent=new Intent(this,
                Example2Activity.class);
        //startActivity() 메소드를 호출하면서 Intent 객체를 전달한다.
        startActivity(intent);
    }
}






