// IRemoteServiceCallback.aidl
package com.solgae.qvingservice;

// Declare any non-default types here with import statements

oneway interface IRemoteServiceCallback {
    void valueChanged(String command);
}
