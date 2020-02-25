package com.gura.step11contentresolver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
                    implements View.OnClickListener{
    //필요한 필드 정의하기
    private EditText editText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.editText);
        listView=findViewById(R.id.listView);
        adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );
        listView.setAdapter(adapter);

        //버튼의 참조값 얻어와서 리스너 등록하기
        Button findBtn=findViewById(R.id.findBtn);
        findBtn.setOnClickListener(this);
    }
    //연락처 정보를 가져오는 메소드
    public void getContacts(){
        //출력한 데이터를 한번 clear 하고 읽어온다.
        list.clear();

        //ContentResolver 객체의 참조값 얻어오기
        ContentResolver resolver=getContentResolver();
        //연락처 정보의 Uri 의 참조값 얻어오기 (상수 객체로 미리 정의 되어 있음)
        Uri contactUri=
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //원하는 칼럼명 ( 아이디PK, 전화번호, 이름)
        String[] columns={
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        };

        //키워드 읽어오기
        String keyword=editText.getText().toString();
        //where 절에 들어갈 조건
        String where=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+
                " LIKE ?";
        // ? 에 바인딩할 인자
        String[] args={"%"+keyword+"%"};

        //정렬 (이름에 대해서 오름차순)
        String order=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";

        //원하는 정보를 SELECT 하고 결과값을 Cursor type 으로 받는다.
        // .query(uri, columns, where, args, order)
        Cursor cursor=resolver.query(contactUri, columns,
                where, args, order);

        //Cursor 객체에서 반복문 돌면서 데이터 추출하기
        while(cursor.moveToNext()){
            long id=cursor.getLong(0);
            String phoneNumber=cursor.getString(1);
            String name=cursor.getString(2);
            //연락처 정보를 한줄의 문자열로 구성을 해서
            String info=id+" | "+phoneNumber+" | "+name;
            //모델에 추가한다.
            list.add(info);
        }
        //ListView 가 갱신되도록 아답타에 알린다.
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        int permissionCheck=
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_CONTACTS);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){ //권한이 없다면
            //권한을 얻어야하는 퍼미션 목록
            String[] permissions={Manifest.permission.READ_CONTACTS};
            //권한을 얻어내도록 유도한다.
            ActivityCompat.requestPermissions(this,
                    permissions,
                    0);
        }else{//권한이 있다면
            getContacts();
        }
    }

    //퍼미션을 체크하거나 취소 했을때 호출되는 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0: // 0 번 요청 코드인 경우
                //퍼미션을 Allow 했을경우
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    getContacts();
                }else{// Allow 하지 않았을 경우
                    Toast.makeText(this, "퍼미션을 체크해 주세요",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}







