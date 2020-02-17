package com.gura.step06fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SubActivity extends AppCompatActivity
        implements MyFragment.MyFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }

    @Override
    public void showMessage(int count) {
        new AlertDialog.Builder(this)
                .setMessage("현재 카운트:"+count)
                .setNeutralButton("확인", null)
                .create()
                .show();
    }
}
