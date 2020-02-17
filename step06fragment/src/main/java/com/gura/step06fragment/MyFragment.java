package com.gura.step06fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
/*
    [ Fragment 만드는 방법]

    1. Fragment 클래스를 상속 받는다.
    2. onCreateView() 메소드를 오버라이딩 한다.
 */
public class MyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //인자로 전달되는 레이아웃 전개자 객체를 이용해서 View 객체를 만들어서
        View view=inflater.inflate(R.layout.fragment_my, container);
        //리턴해준다.
        return view;
    }
}




