package com.gura.step06fragment2.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gura.step06fragment2.CountryDto;
import com.gura.step06fragment2.R;


public class PlaceholderFragment extends Fragment {
    //Fragment 객체를 생성해서 리턴해주는 static 메소드
    public static PlaceholderFragment newInstance(CountryDto dto) {
        PlaceholderFragment fr=new PlaceholderFragment();
        //Fragment 에 전달할 Bundle 객체
        Bundle bundle=new Bundle();
        bundle.putSerializable("dto", dto);
        //Fragment 에 인자 전달하기
        fr.setArguments(bundle);
        return fr;
    }
    //국가 정보를 담을 필드
    private CountryDto dto;

    //1. 프레그먼트가 최초 사용될때 호출되는 메소드
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //인자로 Bundle 객체가 전달된다.
        dto=(CountryDto) getArguments().getSerializable("dto");
    }
    //2. 프레그먼트가 활성화 될때마다 호출되는 메소드
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // res/layout/fragment_main.xml 문서를 전개해서 View 객체를 만든다.
        View view=inflater.inflate(R.layout.fragment_main, container,
                false);
        // 이미지뷰의 참조값 얻어와서
        ImageView imageView=view.findViewById(R.id.imageView);
        // 이미지 출력하기
        imageView.setImageResource(dto.getResId());
        // View 객체 리턴해주기
        return view;
    }
}