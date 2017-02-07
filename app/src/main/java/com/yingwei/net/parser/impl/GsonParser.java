package com.yingwei.net.parser.impl;

import com.google.gson.Gson;
import com.yingwei.net.parser.Parser;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by jiahe008 on 2017/2/7.
 */
public class GsonParser<T> implements Parser<T> {

    private Class<T> mClass = null;

    public GsonParser(Class<T> clazz){
        if (clazz == null){
            throw new IllegalArgumentException("Class can't be null");
        }
        this.mClass = clazz;
    }

    @Override
    public T parse(Response response) {
        try {
            Gson gson = new Gson();
            String str = response.body().string();
            T t = gson.fromJson(str, mClass);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
