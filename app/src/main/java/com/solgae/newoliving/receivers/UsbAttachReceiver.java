package com.solgae.newoliving.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbManager;

import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;

public class UsbAttachReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
            BusProvider.getInstance().post(new PushEvent(null,"USB_ATTACH_RECEIVER"));
        }
    }
}
