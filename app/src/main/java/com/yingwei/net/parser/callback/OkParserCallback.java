package com.yingwei.net.parser.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.yingwei.net.parser.Parser;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jiahe008 on 2017/2/7.
 */
public class OkParserCallback<T> implements Callback {

    private static final int CALLBACK_SUCCESSFUL = 0x01;
    private static final int CALLBACK_FAILED = 0x02;

    private Parser<T> mParser;

    public OkParserCallback(Parser<T> mParser) {
        if (mParser == null) {
            throw new IllegalArgumentException("Parser can't be null");
        }
        this.mParser = mParser;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Message message = Message.obtain();
        message.what = CALLBACK_FAILED;
        message.obj = e;
        mHandler.sendMessage(message);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            T parseResult = mParser.parse(response);
            Message message = Message.obtain();
            message.what = CALLBACK_SUCCESSFUL;
            message.obj = parseResult;
            mHandler.sendMessage(message);
        } else {
            Message message = Message.obtain();
            message.what = CALLBACK_FAILED;
            mHandler.sendMessage(message);
        }
    }

    public void onResponse(T t) {
    }

    public void onFailure(IOException e) {
    }

    static class UIHandler<T> extends Handler {

        private WeakReference mWeakReference; //防止内存泄露，使用弱引用

        public UIHandler(OkParserCallback<T> callback) {
            super(Looper.getMainLooper()); //主线程的Looper
            mWeakReference = new WeakReference<>(callback);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CALLBACK_SUCCESSFUL: {
                    T t = (T) msg.obj;
                    OkParserCallback<T> callback = (OkParserCallback<T>) mWeakReference.get();
                    if (callback != null) {
                        callback.onResponse(t);
                    }
                    break;
                }
                case CALLBACK_FAILED: {
                    IOException e = (IOException) msg.obj;
                    OkParserCallback<T> callback = (OkParserCallback<T>) mWeakReference.get();
                    if (callback != null) {
                        callback.onFailure(e);
                    }
                    break;
                }
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    private Handler mHandler = new UIHandler<>(this);



}
