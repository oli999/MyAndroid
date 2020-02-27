package com.gura.step12sharedpref;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity
            implements SharedPreferences.OnSharedPreferenceChangeListener {

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
    //액티비티가 활성화 되기 바로 직전에 호출되는 메소드
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref=
                PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences 가 바뀌었는지 감시할 리스너 등록하기
        pref.registerOnSharedPreferenceChangeListener(this);
    }
    //액티비티가 비활성화 직후 바로 호출되는 메소드
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences pref=
                PreferenceManager.getDefaultSharedPreferences(this);
        //SharedPreferences 가 바뀌었는지 감시할 리스너 등록 해제
        pref.unregisterOnSharedPreferenceChangeListener(this);
    }
    //SharedPreferences 에 저장된 값이 바뀌면 호출되는 메소드
    @Override
    public void onSharedPreferenceChanged(SharedPreferences pref,
                                          String key) {
        // key : 바뀐 설정의 key 값이 전달된다.
        if(key.equals("signature")){
            String signature=pref.getString(key, "");
            Toast.makeText(this, signature,
                    Toast.LENGTH_LONG).show();
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