package com.khavronsky.bottle.Presenters;


import java.lang.ref.WeakReference;

public abstract class AbstractPresenter<T> {

    private WeakReference<T> weakReference;

    T getMyObj() {
        return weakReference != null ? weakReference.get() : null;
    }

    public void setReference(T obj) {
        weakReference = new WeakReference<>(obj);
    }

    public void clearReference() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

}
