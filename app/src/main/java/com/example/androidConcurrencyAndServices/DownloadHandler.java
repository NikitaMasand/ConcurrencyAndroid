package com.example.androidConcurrencyAndServices;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class DownloadHandler extends Handler {
    private static final String TAG = "MyTag" ;
    private final MainActivity mActivity;

    public DownloadHandler(MainActivity mActivity) {
        this.mActivity = mActivity;
    }


    public void handleMessage (Message message) {
        downloadSong(message.obj.toString());
    }

    private void downloadSong(final String song) {
        Log.d(TAG, "run: starting download in a separate thread from main");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivity.log("Download complete, passing reference of main activity "+song);
                mActivity.displayProgressBar(false);
            }
        });

        //performs same work as run on ui thread
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                mActivity.log("Download complete, passing reference of main activity");
//                mActivity.displayProgressBar(false);
//            }
//        });
//        Log.d(TAG, "downloadSong:" + song + "download completed! ");
    }
}
