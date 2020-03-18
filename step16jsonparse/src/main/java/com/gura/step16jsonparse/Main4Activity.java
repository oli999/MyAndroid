package com.gura.step16jsonparse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main4Activity extends AppCompatActivity
                        implements Util.RequestListener,
                        AdapterView.OnItemClickListener,
                        AdapterView.OnItemLongClickListener,
                        View.OnClickListener{
    private MemberAdapter adapter;
    private List<MemberDto> list;
    //static final 상수 정의하기
    public static final int REQUEST_LIST=0;
    public static final int REQUEST_DELETE=1;
    public static final int REQUEST_INSERT=2;
    //EditText
    private EditText inputName, inputAddr;
    //ListView
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //모델 객체 생성
        list=new ArrayList<>();
        //아답타 객체 생성
        adapter=new MemberAdapter(this, R.layout.listview_cell, list);
        //ListView
        listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);
        //스프링 웹서버에 요청하기
        String urlAddr="http://192.168.0.2:8888/spring05/android/member/list.do";
        Util.sendGetRequest(REQUEST_LIST, urlAddr, null, this);
        //ListView  에 아이템 클릭 리스너 등록
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        //EditText  의 참조값을 필드에 저장하고
        inputName=findViewById(R.id.inputName);
        inputAddr=findViewById(R.id.inputAddr);
        //Button 의 참조값 얻어내서 리스너 등록 하기
        Button addBtn=findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        //어떤 요청에 대한 결과인지 switch 문으로 분기한다.
        switch (requestId){
            case REQUEST_LIST:
                String data=(String)result.get("data");
                updateListView(data);
                break;
            case REQUEST_DELETE:
                //스프링 웹서버에 목록을 다시 요청하기
                String urlAddr="http://192.168.0.2:8888/spring05/android/member/list.do";
                Util.sendGetRequest(REQUEST_LIST, urlAddr, null, this);
                break;
            case REQUEST_INSERT:
                String urlAddr2="http://192.168.0.2:8888/spring05/android/member/list.do";
                Util.sendGetRequest(REQUEST_LIST, urlAddr2, null, this);
                break;
        }
    }
    //ListView 를 업데이트 하는 메소드
    public void updateListView(String data){
        list.clear();
        try {
            JSONArray arr=new JSONArray(data);
            //반복문 돌면서
            for(int i=0; i<arr.length(); i++){

                JSONObject obj=arr.getJSONObject(i);
                int num=obj.getInt("NUM");
                String name=obj.getString("NAME");
                //회원정보를 MemberDto 객체를 생성해서 담은 다음
                MemberDto tmp=new MemberDto(num, name, null);
                //모델에 추가
                list.add(tmp);
            }
            //아답타에 모델의 데이터가 바뀌었다고 알려서 ListView 가 업데이트 되도록한다.
            adapter.notifyDataSetChanged();
            //ListView 를 가장 아래 쪽으로 스크롤하기
            listView.smoothScrollToPosition(list.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //1. 클릭한 셀의 회원번호를 읽어와서
        int num=list.get(i).getNum();
        //2. Intent 객체에 담고
        Intent intent=new Intent(this, DetailActivity.class);
        intent.putExtra("num", num);
        //3. DetailActivity 로 이동하기
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        //클릭한 셀에 해당하는 회원정보 얻어오기
        MemberDto dto=list.get(i);
        //삭제할 회원의 번호를 필드에 저장한다.
        selectedNum=dto.getNum();

        new AlertDialog.Builder(this)
                .setMessage(dto.getName()+" 의 정보를 삭제 하겠습니까?")
                .setPositiveButton("확인", listener)
                .setNegativeButton("취소", null)
                .create()
                .show();

        return false;
    }
    //필드
    private int selectedNum; // 삭제할 회원의 번호(PK)

    DialogInterface.OnClickListener listener=
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String urlAddr="http://192.168.0.2:8888/spring05" +
                            "/android/member/delete.do";
                    //삭제할 회원의 번호를 Map 에 담고
                    Map<String, String> map=new HashMap<>();
                    map.put("num", Integer.toString(selectedNum));
                    //Util 을 이용해서 post 방식으로 요청을 한다.
                    Util.sendPostRequest(REQUEST_DELETE, urlAddr, map,
                            Main4Activity.this);
                }
            };
    // 추가 버튼을 눌렀을때 호출되는 메소드
    @Override
    public void onClick(View view) {
        //입력한 이름과 주소를 읽어와서
        String name=inputName.getText().toString();
        String addr=inputAddr.getText().toString();
        //Map 에 담고
        Map<String, String> map=new HashMap<>();
        map.put("name", name);
        map.put("addr", addr);
        //Util 을 이용해서 Post 방식 요청과 함께 전송한다.
        String urlAddr="http://192.168.0.2:8888/spring05" +
                "/android/member/insert.do";
        Util.sendPostRequest(REQUEST_INSERT, urlAddr, map, this);
        //키보드 숨기기
        Util.hideKeyboard(this);
        //포커스 해제
        Util.releaseFocus(inputName);
        Util.releaseFocus(inputAddr);

    }
}

















