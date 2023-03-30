package com.solgae.newoliving.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import com.solgae.newoliving.App;
import com.solgae.newoliving.vo.UsbDataBinder;

public class UsbReceiver extends BroadcastReceiver {

    private static final String ACTION_USB_PERMISSION = "com.dynamsoft.USB_PERMISSION";
    UsbManager mUsbManager;

    public UsbReceiver(UsbManager mUsbManager) {
        this.mUsbManager = mUsbManager;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ACTION_USB_PERMISSION.equals(action)) {
            synchronized (this) {
                UsbDevice device = (UsbDevice) intent
                        .getParcelableExtra(UsbManager.EXTRA_DEVICE);

                if (intent.getBooleanExtra(
                        UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                    if (device != null) {
                        // call method to set up device communication
                        UsbDataBinder binder = new UsbDataBinder(mUsbManager, device);
                        App.hashMap.put(device, binder);
                    }
                } else {
                    // Log.d(TAG, "permission denied for device " + device);
                }
            }
        }
    }
}