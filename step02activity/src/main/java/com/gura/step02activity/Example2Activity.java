package com.gura.step02activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Example2Activity extends AppCompatActivity
                    implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example2);
        //버튼의 참조값 얻어와서 리스너 등록하기
        Button updateBtn=findViewById(R.id.updateBtn);
        Button deleteBtn=findViewById(R.id.deleteBtn);
        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //눌러진 버튼의 아이디값을 읽어온다.
        int resId=view.getId();
        if(resId==R.id.updateBtn){ //수정 버튼을 눌렀을때
            Toast.makeText(this, "수정?", Toast.LENGTH_SHORT).show();
        }else if(resId==R.id.deleteBtn){//삭제 버튼을 눌렀을때
            Toast.makeText(this, "삭제?", Toast.LENGTH_LONG).show();
        }

    }
}







