package com.solgae.newoliving.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import com.solgae.newoliving.App;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;
import com.solgae.newoliving.vo.UsbDataBinder;


public class UsbDetachReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
            UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
            if (device != null) {
                // call your method that cleans up and closes communication with the device
                UsbDataBinder binder = App.hashMap.get(device);
                if (binder != null) {
                    binder.onDestroy();
                    App.hashMap.remove(device);
                }
            }
        }

        BusProvider.getInstance().post(new PushEvent(null,"USB_DETACH_RECEIVER"));
    }
}
