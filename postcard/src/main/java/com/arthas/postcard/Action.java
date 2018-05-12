package com.arthas.postcard;
public abstract class Action<T> {

    public abstract void process(Postcard postcard,T data);


}
