package com.arthas.fine_device;



/**
 * Created by Administrator on 2016/5/10.
 */
public class MyCache
{
    public static ACache mCache;

    public static ACache getACache()
    {
        if (mCache == null)
        {
            mCache = ACache.get(Logic.getApp());
        }
        return mCache;
    }
}
