package com.example.crypsis.mchat;

import android.os.Binder;

import java.lang.ref.WeakReference;

/**
 * Created by crypsis on 2/12/16.
 */

public class LocalBinder<S> extends Binder {
    private final WeakReference<S> mService;

    public LocalBinder(final S service) {
        mService = new WeakReference<S>(service);
    }

    public S getService() {
        return mService.get();
    }

}
