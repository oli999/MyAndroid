package com.gura.step04customlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
    [ ListView  에 연결할 아답타 클래스 정의하기 ]

    - BaseAdapter 추상 클래스를 상속 받아서 만든다.
 */
public class CountryAdapter extends BaseAdapter {
    //필요한 필드 정의하기
    private Context context;
    private int layoutRes;
    private List<CountryDto> list;
    private LayoutInflater inflater;

    //생성자
    public CountryAdapter(Context context, int layoutRes,
                          List<CountryDto> list){
        this.context=context;
        this.layoutRes=layoutRes;
        this.list=list;
        /*
            [ 레이아웃 전개자 객체 ]
            xml 로 정의한 레이아웃 정보를 실제로 전개해서 View 객체로
            만들어 주는 객체
         */
        inflater=LayoutInflater.from(context);
    }

    //전체 모델의 갯수를 리턴해 준다.
    @Override
    public int getCount() {
        return list.size();
    }
    //i 인덱스에 해당하는 아이템(data)를 리턴해 준다.
    @Override
    public Object getItem(int i) {
        return list.get(i);
    }
    //i 인덱스에 해당하는 아이템의 아이디가 있으면 리턴해준다.
    @Override
    public long getItemId(int i) {
        return i;
    }
    //i 인덱스에 해당하는 셀 View 를 리턴해준다.
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //처음에는 인자로 선언된 지역변수 view 에 null 이 들어 있다.
        if(view == null){
            //레이아웃 전개자 객체를 이용해서 View 객체를 만든다.
            view=inflater.inflate(layoutRes, viewGroup, false);
        }
        //View 에서 원하는 UI 의 참조값을 얻어낸다.
        ImageView imageView=view.findViewById(R.id.imageView);
        TextView textView=view.findViewById(R.id.textView);
        // i 번째 인덱스에 해당하는 데이터를 얻어온다.
        CountryDto dto=list.get(i);
        // View 에 데이터를 출력한다.
        imageView.setImageResource(dto.getResId());
        textView.setText(dto.getName());
        // 구성된 View 객체를 리턴해준다.
        return view;
    }
}











