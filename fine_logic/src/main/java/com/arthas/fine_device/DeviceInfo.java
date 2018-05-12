package com.arthas.fine_device;

import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;




public class DeviceInfo
{

    public static float scaledDensity;
    public static int sdkVersion = Build.VERSION.SDK_INT;
    public static int screenDip;
    private static int screenWidth;
    private static int screenHeight;
    private static float density;
    private static String versionName;
    private static int versionCode;


    public static int getScreenHeight()
    {
        validate();
        return screenHeight;
    }

    public static int getScreenWidth()
    {
        validate();
        return screenWidth;
    }


    public static String getVersionName()
    {
        if (versionName == null)
        {
            versionName = AppModule.$vername();
        }
        return versionName;
    }

    public static int getVersionCode()
    {
        if (versionCode == 0)
        {
            versionCode = AppModule.$vercode();
        }
        return versionCode;
    }

    private static void validate()
    {
        if (screenWidth == 0)
        {
            init();
        }
    }

    public static float getDensity()
    {
        validate();
        return density;
    }


    public static float getScreenDip()
    {
        validate();
        return screenDip;
    }


    public static int getSdkVersion()
    {
        return Build.VERSION.SDK_INT;
    }


    public static int dp2px(float dp)
    {
        validate();
        return (int) (density * dp);
    }


    public static void init()
    {
        Resources res = Logic.getApp().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
        density = dm.density;
        scaledDensity = dm.scaledDensity;

        screenDip = (int) (screenWidth / density);

    }


    public static boolean is6_0()

    {
        return  DeviceInfo.sdkVersion >= 23;
    }

}
