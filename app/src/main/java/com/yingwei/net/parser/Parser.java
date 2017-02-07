package com.yingwei.net.parser;

import okhttp3.Response;

/**
 * Created by jiahe008 on 2017/2/7.
 */
public interface Parser<T> {
    T parse(Response response);
}
