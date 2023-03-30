package com.solgae.newoliving.receivers;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.solgae.newoliving.App;
import com.solgae.newoliving.busevent.BusProvider;
import com.solgae.newoliving.busevent.PushEvent;

/**
 * 스케쥴 다운로드가 완료되었다면
 */
public class ScheduleDownload extends BroadcastReceiver {

    Context context;
    App app;


    public ScheduleDownload(Context context) {
        this.context = context;
        app = new App();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        Log.e("IN", "" + referenceId);
        app.download_id_list.remove(referenceId);
        if (app.download_id_list.isEmpty())
        {
            Log.e("INSIDE", "" + referenceId);
            //파일 다운로드 에러
            BusProvider.getInstance().post(new PushEvent(null,"SCHEDULE_FILE_DOWNLOADED"));

        }
    }
}
