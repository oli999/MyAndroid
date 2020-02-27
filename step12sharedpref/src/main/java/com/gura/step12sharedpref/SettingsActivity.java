package com.gura.step12sharedpref;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res/layout/settings_activity.xml 문서를 전개해서 화면 구성하기
        setContentView(R.layout.settings_activity);
        /*
            id 가 settings 인 레이아웃(FrameLayout) 에
            SettingsFragment 로 화면 구성하게 하기
         */
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        // 좌상단 up 버튼 동작 가능하도록 한다.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    //FrameLayout 을 채출 프레그먼트
    public static class SettingsFragment extends PreferenceFragmentCompat {
        //프레그먼트가 처음 활성화 될때 호출되믄 메소드
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // res/xml/root_preferences.xml 문서를 전개해서 프레그먼트 UI 구성하기
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

        }

    }
}