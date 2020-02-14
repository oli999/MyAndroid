package com.gura.step04customlist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
/*
    [ ListView  에 연결할 아답타 클래스 정의하기 ]

    - BaseAdapter 추상 클래스를 상속 받아서 만든다.
 */
public class CountryAdapter extends BaseAdapter {
    //전체 모델의 갯수를 리턴해 준다.
    @Override
    public int getCount() {
        return 0;
    }
    //i 인덱스에 해당하는 아이템(data)를 리턴해 준다.
    @Override
    public Object getItem(int i) {
        return null;
    }
    //i 인덱스에 해당하는 아이템의 아이디가 있으면 리턴해준다.
    @Override
    public long getItemId(int i) {
        return 0;
    }
    //i 인덱스에 해당하는 셀 View 를 리턴해준다.
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
