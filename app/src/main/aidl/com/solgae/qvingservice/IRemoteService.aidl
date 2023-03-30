// IRemoteServiceBinder.aidl
package com.solgae.qvingservice;

// Declare any non-default types here with import statements
import com.solgae.qvingservice.IRemoteServiceCallback;

interface IRemoteService {

    boolean registerCallback(IRemoteServiceCallback callback);
    boolean unregisterCallback(IRemoteServiceCallback callback);
// Update Apk
    boolean setServerInfo(String apk_download_files_location);

    boolean check_internet();
    boolean app_state(String state);
}
