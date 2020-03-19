package com.gura.step09camera;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListActivity extends AppCompatActivity
                    implements Util.RequestListener{
    public static final String IMAGELIST_URL="http://192.168.0.5:8888/spring05/android/image/list.do";;
    private ListView listView;
    private ImageAdapter adapter;
    private List<ImageDto> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView=findViewById(R.id.listView);
        list=new ArrayList<>();
        adapter=new ImageAdapter(this, R.layout.listview_cell, list);
        listView.setAdapter(adapter);
        Util.sendGetRequest(0, IMAGELIST_URL, null, this);
    }

    @Override
    public void onSuccess(int requestId, Map<String, Object> result) {
        String data=(String)result.get("data");
        try{
            JSONArray arr=new JSONArray(data);
            for(int i=0; i<arr.length(); i++){
                JSONObject obj=arr.getJSONObject(i);
                int num=obj.getInt("num");
                String writer=obj.getString("writer");
                String imagePath=obj.getString("imagePath");
                String regdate=obj.getString("regdate");
                ImageDto dto=new ImageDto();
                dto.setNum(num);
                dto.setImageUrl("http://192.168.0.5:8888/spring05"+imagePath);
                dto.setWriter(writer);
                dto.setRegdate(regdate);
                list.add(dto);
            }
            adapter.notifyDataSetChanged();
        }catch (JSONException je){

        }

    }

    @Override
    public void onFail(int requestId, Map<String, Object> result) {

    }
}
