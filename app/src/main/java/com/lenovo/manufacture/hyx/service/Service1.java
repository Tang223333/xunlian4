package com.lenovo.manufacture.hyx.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class Service1 extends Service {
    private static final String TAG = "Service1";
    private final IBinder iBinder = new LocalBinder();
    public class LocalBinder extends Binder{
        public Service1 getService(){
            return Service1.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: 欢迎光临");

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.d(TAG, "onBind: 绑定开始");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: 绑定结束");
        return false;
    }
}

















































//                          ___====-_  _-====___
//                    _--^^^#####//      \\#####^^^--_
//                 _-^##########// (    ) \\##########^-_
//                -############//  |\^^/|  \\############-
//              _/############//   (@::@)   \\############\_
//             /#############((     \\//     ))#############\
//            -###############\\    (oo)    //###############-
//           -#################\\  / VV \  //#################-
//          -###################\\/      \//###################-
//         _#/|##########/\######(   /\   )######/\##########|\#_
//         |/ |#/\#/\#/\/  \#/\##\  |  |  /##/\#/  \/\#/\#/\#| \|
//         `  |/  V  V  `   V  \#\| |  | |/#/  V   '  V  V  \|  '
//            `   `  `      `   / | |  | | \   '      '  '   '
//                             (  | |  | |  )
//                            __\ | |  | | /__
//                           (vvv(VVV)(VVV)vvv)
//                                 神兽保佑
//                                代码无BUG!
