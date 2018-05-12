package com.arthas.fine_device;

import android.view.Gravity;
import android.widget.Toast;




public class MyToast
{

    private volatile static Toast mToast;

    private static Toast getToast()
    {
        if (mToast == null)
        {
            mToast =  Toast.makeText(Logic.getApp(),"",Toast.LENGTH_SHORT);
        }
        return mToast;
    }


    public static void showServerError()
    {
        show("服务器超时!");
    }

    //
    public static void showNoNetError()
    {
        show("网络失去连接！");
    }


    public static void show(String string)
    {
        if (string==null||string.length() ==0)
            return;

        final Toast toast = getToast();
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(string);
        toast.show();
    }

    public static void showBottom(String string)
    {
        if (string==null||string.length() ==0)
            return;

        final Toast toast = getToast();
        toast.setGravity(Gravity.BOTTOM, 0, DeviceInfo.dp2px(56));
        toast.setText(string);
        toast.show();
    }

}
