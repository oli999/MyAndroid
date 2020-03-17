package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity
                            implements Util.RequestListener{
    //필드
    private TextView textNum,textName,textAddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Intent 객체에 "num" 이라는 키값으로 담긴 회원의 번호 얻어내기
        int num=getIntent().getIntExtra("num", 0);
        // 회원 번호를 Map 객체에 담는다.
        Map<String, String> map=new HashMap<>();
        map.put("num", Integer.toString(num));
        String urlAddr="http://192.168.0.2:8888/spring05/android/member/detail.do";
        //Util 을 이용해서 요청을한다.
        Util.sendGetRequest(0, urlAddr, map, this);

        //TextView 의 참조값 얻어내서 필드에 저장하기
        textNum=findViewById(R.id.textNum);
        textName=findViewById(R.id.textName);
        textAddr=findViewById(R.id.textAddr);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        /*
            data 는
            {"num":1, "name":"김구라", "addr":"노량진"}
            형식의 JSON 문자열이다.
         */
        String data=(String)result.get("data");
        try{
            JSONObject obj=new JSONObject(data);
            int num=obj.getInt("num");
            String name=obj.getString("name");
            String addr=obj.getString("addr");
            //TextView 에 출력하기
            textNum.setText(Integer.toString(num));
            textName.setText(name);
            textAddr.setText(addr);

        }catch(JSONException je){}
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}
