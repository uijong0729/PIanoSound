package service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;

import me.euijonglee.realrealsound.R;

public class RecordTimer extends Service {

    private View mView;
    private WindowManager mManager;
    Chronometer ch;

    @Override
    public void onCreate() {
        super.onCreate();

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.timerlayout, null);
        ch = mView.findViewById(R.id.chronometer);


        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                // 항상 최상위 화면에 있도록 설정합니다
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                // 터치 이벤트를 받지 않습니다
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT); // 투명

        mManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mManager.addView(mView, mParams);
        ch.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mView != null) {
            mManager.removeView(mView);
            mView = null;
        }
        if(ch != null){
        ch.stop();
        ch.setBase(0);}
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}

