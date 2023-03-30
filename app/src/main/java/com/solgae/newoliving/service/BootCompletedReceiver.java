package com.solgae.newoliving.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.solgae.newoliving.base.IntroActivity;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent arg1) {
        // TODO Auto-generated method stub
        Log.w("boot_broadcast_poc", "starting service...");
        Intent mintent = new Intent(context, IntroActivity.class);
        context.startActivity(mintent);
    }


}