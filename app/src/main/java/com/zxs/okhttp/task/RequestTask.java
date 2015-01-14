package com.zxs.okhttp.task;

import android.util.Log;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zxs on 15/1/14.
 */
public class RequestTask {
    final OkHttpClient okHttpClient = new OkHttpClient();
    public  void getTask(final String url){

        for(int i=0;i<1;i++) {
            MyThread thread = new MyThread(i);
            thread.start();
        }
    }
    public class MyThread extends Thread{
        int num;
        public MyThread(int i){
            num = i;
        }
        @Override
        public void run() {
            super.run();
            try {
                Request request = new Request.Builder()
                        //http://publicobject.com/helloworld.txt
                        .url("http://publicobject.com/helloworld.txt")
                        .build();

                Response response = okHttpClient.newCall(request).execute();
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                System.out.println("请求次数: " + num);
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                System.out.println(response.body().string());
            } catch (IOException E) {
            }
        }
    }
}
