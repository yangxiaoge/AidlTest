package com.yjn.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Author:0027008122 [yang.jianan@zte.com.cn]
 * Date:2016-04-28
 * Time:16:17
 * Version:1.0
 * TaskId:
 */
public class RemoteService extends Service {
    private static final String TAG = "RemoteService";
    private ServiceAidl.Stub mBinder = new ServiceAidl.Stub() {

        @Override
        public int add(int a, int b) throws RemoteException {
            Log.i(TAG,"收到服务器的计算请求！");
            return a + b;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "Bind -> Current Process From Server: " + android.os.Process.myPid());
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "Unbind -> Current Process From Server: " + android.os.Process.myPid());
        return super.onUnbind(intent);
    }
}
