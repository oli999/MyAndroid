package com.gura.step06fragment2.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gura.step06fragment2.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    //이미지 리소스 아이디를 int[]  에 미리 준비 해두기
    private int[] imgs={R.drawable.austria, R.drawable.belgium,
        R.drawable.brazil, R.drawable.france, R.drawable.germany};

    private final Context mContext;
    //생성자
    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    //인자로 전달하는 인덱스에 해당하는 Fragement 객체의 참조값을 리턴해주는 메소드
    @Override
    public Fragment getItem(int position) {
        //position 인덱스에 해당하는 이미지 리소스 아이디
        int resId=imgs[position];
        //position 인덱스에 해당하는 프레그먼트의 참조값 얻어내서
        PlaceholderFragment fr=PlaceholderFragment.newInstance(resId);
        //리턴해주기
        return fr;
    }
    //인자로 전달되는 인덱스에 해당하는 문자열(페이지 제목, tab 제목)을 리턴해주는 메소드
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title=Integer.toString(position+1);
        return title;
    }
    //전체 페이지의 갯수를 리턴해 준다.
    @Override
    public int getCount() {
        // Show 5 total pages.
        return 5;
    }
}