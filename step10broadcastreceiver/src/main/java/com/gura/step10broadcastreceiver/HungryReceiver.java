package com.gura.step10broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class HungryReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity activity=(MainActivity)context;
        activity.updateUI("배고파유~");
    }
}
