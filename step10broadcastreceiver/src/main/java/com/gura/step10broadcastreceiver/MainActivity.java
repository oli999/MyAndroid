package com.gura.step10broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyReceiver mr=new MyReceiver();
        registerReceiver(mr,
                new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
    }
}
