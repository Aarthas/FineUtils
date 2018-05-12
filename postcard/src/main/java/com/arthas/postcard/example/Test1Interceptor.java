package com.arthas.postcard.example;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

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

public class Test1Interceptor implements IInterceptor {

    /**
     * The operation of this interceptor.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {
        Context context = postcard.getContext();
//        System.out.println("Test1Interceptor  1"+postcard.getExtras());
//        callback.onInterrupt(new Exception("aa"));
        final AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setCancelable(false);
        ab.setTitle("温馨提醒");
        ab.setMessage("想要跳转到Test4Activity么？(触发了\"/inter/test1\"拦截器，拦截了本次跳转)");
        ab.setNegativeButton("继续", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onContinue(postcard);
            }
        });
        ab.setNeutralButton("算了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.onInterrupt(null);
            }
        });
        ab.setPositiveButton("加点料", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                postcard.withString("extra", "我是在拦截器中附加的参数");
//                postcard.withString("bb","ba");
                callback.onContinue(postcard);
            }
        });
        ab.show();

    }




}
