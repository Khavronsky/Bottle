package com.khavronsky.bottle;


import java.lang.ref.WeakReference;

public abstract class AbstractPresenter<T> {

    private WeakReference<T> weakReference;

    T getMyObj() {
        return weakReference != null ? weakReference.get() : null;
    }

    void setReference(T obj) {
        weakReference = new WeakReference<>(obj);
    }

    void clearReference() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

}
