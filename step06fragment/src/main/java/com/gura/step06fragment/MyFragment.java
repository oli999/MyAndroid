package com.gura.step06fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/*
    [ Fragment 만드는 방법]

    1. Fragment 클래스를 상속 받는다.
    2. onCreateView() 메소드를 오버라이딩 한다.
 */
public class MyFragment extends Fragment
                        implements View.OnTouchListener {
    //터치 횟수를 관리할 필드
    private int touchCount;
    //TextView 의 참조값
    private TextView textView;
    private MainActivity activity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //인자로 전달되는 레이아웃 전개자 객체를 이용해서 View 객체를 만들어서
        View view=inflater.inflate(R.layout.fragment_my, container);
        //TextView 의 참조값 얻어오기
        textView=view.findViewById(R.id.textView);
        //TextView 에 터치 리스너 등록하기
        textView.setOnTouchListener(this);
        //해당 프레그먼트를 관리하는 액티비티의 참조값
        activity=(MainActivity) getActivity();

        //리턴해준다.
        return view;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //1. 터치 카운트를 1 증가 시키다.
        touchCount++;
        //2. TextView 에 출력하기
        textView.setText(Integer.toString(touchCount));
        //3. 액티비티의 메소드 호출하면서 카운트 전달하기
        if(touchCount%10 == 0){
            activity.showMessage(touchCount);
        }
        return false;
    }
}




