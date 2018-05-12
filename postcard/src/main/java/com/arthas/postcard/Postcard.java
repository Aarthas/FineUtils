package com.arthas.postcard;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ExitDialogFilter container that contains the roadmap.
 *
 * @author Alex <a href="mailto:zhilong.liu@aliyun.com">Contact me.</a>
 * @version 1.1.0
 * @since 16/8/22 19:16
 */
public final class Postcard  {
    // Base

    private Object tag;             // ExitDialogFilter tag prepare for some thing wrong.
    private HashMap mBundle;         // Data to transform
    private int flags = -1;         // Flags of route

    private IProvider provider;     // It will be set value, if this postcard was provider.
    private boolean greenChannel;
    Context context;
    private Object data;
    private ILifeCircle iLifeCircle;
    private OnInterrupt onInterrupt;

    public OnInterrupt getOnInterrupt() {
        return onInterrupt;
    }

    public ILifeCircle getiLifeCircle() {
        return iLifeCircle;
    }

    public Postcard withContext(Context context) {
        this.context = context;
        return this;
    }

    public Context getContext() {
        return context;
    }

    ArrayList<IInterceptor> filters = new ArrayList();

    private Action action;


    public IProvider getProvider() {
        return provider;
    }

    public Postcard setProvider(IProvider provider) {
        this.provider = provider;
        return this;
    }

    public Postcard() {

    }
    public static Postcard build( ) {
        Postcard postcard = new Postcard();
        return postcard;
    }

    public HashMap getMap() {
        return mBundle;
    }

    public boolean isGreenChannel() {
        return greenChannel;
    }

    public Object getTag() {
        return tag;
    }

    public Postcard setTag(Object tag) {
        this.tag = tag;
        return this;
    }



    /**
     * Green channel, it will skip all of interceptors.
     *
     * @return this
     */
    public Postcard greenChannel() {
        this.greenChannel = true;
        return this;
    }

    /**
     * BE ATTENTION TO THIS METHOD WAS <P>SET, NOT ADD!</P>
     */
    public Postcard withMap(HashMap bundle) {
        if (null != bundle) {
            mBundle = bundle;
        }

        return this;
    }

    public Postcard withFilters(ArrayList<IInterceptor> arrayList) {
        filters.addAll(arrayList);
        return this;
    }

    public void send() {
        _Postcard.navigate(filters,this,action);

    }

    public void send(Action action) {
        _Postcard.navigate(filters,this,action);

    }
    public Postcard withAction(Action action) {
        this.action = action;
        return this;
    }

    public Action getAction() {
        return action;
    }

    public Postcard withFilter(IInterceptor iInterceptor) {
        filters.add(iInterceptor);
        return this;
    }

    public Postcard withData(Object data) {
        this.data=data;
        return this;
    }

    public <E> E  getData( ) {
        return (E)data;
    }



    public Postcard withFlags( int flag) {
        this.flags = flag;
        return this;
    }

    public int getFlags() {
        return flags;
    }



    public Postcard withLifeCicle(ILifeCircle iLifeCircle) {
        this.iLifeCircle=iLifeCircle;
        return this;
    }

    public Postcard catchInterrupt(OnInterrupt onInterrupt) {
        this.onInterrupt=onInterrupt;
        return this;
    }
}
