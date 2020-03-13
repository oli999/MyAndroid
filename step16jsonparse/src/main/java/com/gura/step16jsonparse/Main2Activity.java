package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Main2Activity extends AppCompatActivity
                            implements View.OnClickListener,
                                    Util.RequestListener{
    //필드
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        result=findViewById(R.id.result);
        //버튼의 참조값 얻어와서 리스너 등록하기
        Button getBtn=findViewById(R.id.getBtn);
        getBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String urlAddr="http://192.168.0.2:8888/spring05/android/getdetail.do";
        Util.sendGetRequest(0, urlAddr, null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        // {"num":1, "name":"김구라", "isMan": true}
        String data=(String)result.get("data");
        try{
            // {} 형식이므로 JSONObject 객체를 생성한다.
            JSONObject obj=new JSONObject(data);
            // 키값을 이용해서 값 추출하기
            int num=obj.getInt("num");
            String name=obj.getString("name");
            boolean isMan=obj.getBoolean("isMan");
            String info="번호:"+num+" 이름:"+name+" 남자여부:"+isMan;
            //TextView 에 출력하기
            this.result.setText(info);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}
