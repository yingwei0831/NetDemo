package com.yingwei.net.parser.impl;

import com.yingwei.net.parser.Parser;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by jiahe008 on 2017/2/7.
 */
public class StringParser implements Parser<String> {

    @Override
    public String parse(Response response) {
        String result = null;
        try {
            result = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
