package com.gura.step03listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
                    implements AdapterView.OnItemClickListener {
    //모델의 참조값을 저장할 필드
    List<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ListView 의 참조값 얻어오기
        ListView listView=findViewById(R.id.listView);
        //아답타에 연결할 모델(Data)
        names=new ArrayList<>();
        //모델에 sample 데이터 저장
        for(int i=0; i<100; i++){
            names.add("김구라"+i);
        }
        // ListView 에 연결할 아답타 객체 생성하기
        ArrayAdapter<String> adapter=
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        names);
        //ListView 에 아답타 연결하기
        listView.setAdapter(adapter);
        //ListView 에 아이템 클릭 리스너 등록하기
        listView.setOnItemClickListener(this);
    }
    //ListView 의 셀을 클릭하면 호출되는 메소드
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i,
                            long l) {
        //인자로 전달되는 i 에는 클릭한 셀의 인덱스값이 들어 있다.
//        new AlertDialog.Builder(this)
//                .setMessage(names.get(i))
//                .setNeutralButton("확인", null)
//                .create()
//                .show();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(names.get(i));
        builder.setNeutralButton("확인", null);
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    //다음 예제 버튼을 눌렀을때 호출되는 메소드
    public void moveNext(View v){
        //Main2Activity 로 이동할 의도 객체 생성
        Intent i=new Intent(this, Main2Activity.class);
        //startActivity() 메소드 호출하면서 의도 객체를 전달하면 이동된다.
        startActivity(i);
    }
}









