package com.gura.step16jsonparse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
                        implements Util.RequestListener{
    //필드
    private ArrayAdapter<String> adapter;
    private List<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //모델
        names=new ArrayList<>();
        //아답타
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                names
        );
        //ListView
        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(adapter);
        //스프링 웹서버에 요청하기
        String urlAddr="http://192.168.0.2:8888/spring05/android/getnames.do";
        Util.sendGetRequest(0, urlAddr, null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        // ["김구라","해골","원숭이"] 형태의 json 문자열 이다.
        String data=(String)result.get("data");
        // [] 형식은 JSONArray 에 대응된다.
        try {
            JSONArray arr=new JSONArray(data);
            //반복문 돌면서
            for(int i=0; i<arr.length(); i++){
                //JSONArray 에서 i 번째 String (문자열을 얻어낸다)
                String tmp=arr.getString(i);
                //모델에 추가
                names.add(tmp);
            }
            //아답타에 모델의 데이터가 바뀌었다고 알려서 ListView 가 업데이트 되도록한다.
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }
}








