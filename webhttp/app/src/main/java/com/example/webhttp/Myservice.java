package com.example.webhttp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Myservice extends Service {
    public Myservice() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}