package com.solgae.newoliving.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;


public class FcmMessagingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getAction();
        String nfc_title = intent.getStringExtra("Notification Title");
        String nfc_body  = intent.getStringExtra("Notification Body");
        String dat_command = intent.getStringExtra("Data command");
        String dat_msg   = intent.getStringExtra("Data message");
/**
 * command = FCM_TICKER_ON
 * dat_msg = color(dc143c)^message
 */
        if(name.equals("com.google.firebase.MESSAGING_EVENT")){
//            BusProvider.getInstance().post(new PushEvent(nfc_title, nfc_body));
            BusProvider.getInstance().post(new PushEvent(dat_command, dat_msg));

        }
    }
}

// 기기 재부팅 : "SYSTEM_REBOOT", "0"
// 칼럼 오픈 : "REMOTE_COL_OPEN","칼럼번호"

// {"to":"eRDPOZ-gb3s:APA91bE9hLmw-_by3ZRB9cKoKPZJr8Ou2hbpVTOOiSAip2DtDIOU-1DFX7JS9aw4dn8FoLZuuWmbKDoDtXkmVEn0gCHZeCU-0kU_2-e5rA8bWNhgtDlB7ydmlfxA62Tiyh5S5aEptZy3"
//"notification":
//    {"body":"0", "title":"SYSTEM_REBOOT"}
//}