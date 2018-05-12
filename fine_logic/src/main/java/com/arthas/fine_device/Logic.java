package com.arthas.fine_device;

import android.app.Application;

public class Logic {

    public static Application app;
    public static void init( Application app){
        Logic.app = app;
    }

    public static Application getApp() {
        return app;
    }
}
