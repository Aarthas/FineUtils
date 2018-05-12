package com.arthas.postcard;

public abstract class Interceptor<T> implements IInterceptor {
    public void preHandler(Postcard postcard, T data) {

    }

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        process(postcard, (T) postcard.getData(), callback);

    }

    public abstract void process(Postcard postcard, T data, InterceptorCallback callback);


    public void afterCompletion(Postcard postcard, T data) {

    }
}
