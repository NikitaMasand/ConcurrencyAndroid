package com.example.androidConcurrencyAndServices;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.helloworld.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyTag";
    private ScrollView mScroll;
    private TextView mLog;
    private ProgressBar mProgressBar;
    private Handler mHandler;
    private String MESSAGE_KEY;
    DownloadThread mDownloadThread;
    private ExecutorService mExecutor;



//    this onCreate was to demonstrate handlers, looper, message queue etc.
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initViews();
//        mLog.setText(R.string.lorem_ipsum);
//        mHandler = new Handler(getMainLooper()) {
//            @Override
//            public void handleMessage(Message message) {
//                String data = message.getData().getString(MESSAGE_KEY);
//                Log.d(TAG, "handleMessage: "+data);
//            }
//        };

//        mDownloadThread = new DownloadThread(MainActivity.this);
//        mDownloadThread.start(); // if we use thread.run(), separate thread won't run, it would use main thread only

//    }

    private void initViews() {
        mScroll = (ScrollView) findViewById(R.id.scrollLog);
        mLog = (TextView) findViewById(R.id.tvLog);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    //this oncreate is to demonstrate executor service
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        mExecutor = Executors.newFixedThreadPool(5);

    }
    //this runcode is to demonstrate executor service
    public void runCode (View view) {
        for(int i=0; i<10; i++) {
            Work work = new Work(i+1);
            mExecutor.execute(work);
        }
    }

    // this runCode was to demonstrate handlers, looper, message queue etc.
//    public void runCode(View v) {
//        log("Running code");
//        displayProgressBar(true);
//
//        for(String song: Playlist.songs){
//            Message message = Message.obtain();
//            message.obj = song;
//            mDownloadThread.mHandler.sendMessage(message);
//        }
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        log("Download complete......run on ui thread");
//                        displayProgressBar(false);
//                    }
//                });
//            }
//        });

//        thread.start();
        //send message to donwload handler
//        for(String song: Playlist.songs) {
//            Message message = Message.obtain();
//            message.obj = song;
//            mDownloadThread.mHandler.sendMessage(message);
//        }


//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//               Log.d(TAG, "run: starting download in a separate thread from main");
//                try {
//                    Thread.sleep(4000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                Message message = new Message();
//                Bundle bundle = new Bundle();
//                bundle.putString(MESSAGE_KEY,"Download completed");
//                message.setData(bundle);
//                mHandler.sendMessage(message);
//                Log.d(TAG, Thread.currentThread().getName());
//                Log.d(TAG, "run: Download Completed!");
//               displayProgressBar(false);
//            }
//        };

//        Thread thread = new Thread(runnable);
//
//        Handler handler = new Handler();
//        handler.postDelayed(runnable, 4000);
//    }
    public void clearOutput(View v) {
        mLog.setText("");
        scrollTextToEnd();
    }

    public void log (String message) {
        Log.i(TAG, message);
        mLog.append(message + "\n");
        scrollTextToEnd();
    }

    private void scrollTextToEnd() {
        mScroll.post(new Runnable() {
            @Override
            public void run() {
                mScroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void displayProgressBar(boolean display) {
        if(display){
            mProgressBar.setVisibility(View.VISIBLE);
        }
        else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }


}

/*
Why Can't access UI on Background Thread
 */