package com.example.androidConcurrencyAndServices;
import android.os.Looper;

public class DownloadThread extends Thread {
    private static final String TAG = "myTag";
    private MainActivity mActivity;
     DownloadHandler mHandler;

    public DownloadThread(MainActivity activity) {
        mActivity = activity;
    }
//    private final String songName;
 // not a good user experience to download the songs all songs together
//    DownloadThread(String song) {
//        this.songName = song;
//    }

//    public void run() {
//        for(String song: Playlist.songs) {
//            downloadSong(song);
//        }
//    }

    public void run() {
        //creating message queue
        Looper.prepare();
        mHandler = new DownloadHandler(this.mActivity);
        //looping over the message queue
        Looper.loop();

    }


}
