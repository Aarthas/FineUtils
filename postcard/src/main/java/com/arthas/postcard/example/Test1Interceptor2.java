package com.arthas.postcard.example;

import android.content.Context;

import com.arthas.postcard.IInterceptor;
import com.arthas.postcard.InterceptorCallback;
import com.arthas.postcard.Postcard;

/**
 * TODO feature
 *
 * @author Alex <a href="mailto:zhilong.lzl@alibaba-inc.com">Contact me.</a>
 * @version 1.0
 * @since 2017/1/3 11:20
 */

public class Test1Interceptor2 implements IInterceptor {
    Context mContext;

    /**
     * The operation of this interceptor.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        System.out.println("Test1Interceptor  2");
//        System.out.println("Test1Interceptor  2"+postcard.getExtras());
        callback.onContinue(postcard);

    }


}
