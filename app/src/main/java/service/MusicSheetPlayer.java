package service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import Utility.FileManager;
import Utility.Note;
import me.euijonglee.realrealsound.MainActivity;
import me.euijonglee.realrealsound.R;

import static me.euijonglee.realrealsound.MainActivity.createSoundPool;

public class MusicSheetPlayer extends Service {
    public MusicSheetPlayer() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {


        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread thread = new Thread(() -> {

            String arString = intent.getStringExtra("jsonArray");

            try {
                JSONArray ja = new JSONArray(arString);
                ArrayList<Note> ar = FileManager.convertJsonToArrayList(ja);

                if(MainActivity.soundPool == null)
                {
                    createSoundPool();

                    //도 레 미 파 솔 라 시 도
                    MainActivity.touchedSound[0] = MainActivity.soundPool.load(this, R.raw.doo, 1);
                    MainActivity.touchedSound[1] = MainActivity.soundPool.load(this, R.raw.re, 1);
                    MainActivity.touchedSound[2] = MainActivity.soundPool.load(this, R.raw.mi, 1);
                    MainActivity.touchedSound[3] = MainActivity.soundPool.load(this, R.raw.pa, 1);
                    MainActivity.touchedSound[4] = MainActivity.soundPool.load(this, R.raw.sol, 1);
                    MainActivity.touchedSound[5] = MainActivity.soundPool.load(this, R.raw.ra, 1);
                    MainActivity.touchedSound[6] = MainActivity.soundPool.load(this, R.raw.si, 1);
                    MainActivity.touchedSound[7] = MainActivity.soundPool.load(this, R.raw.hdo, 1);
                    MainActivity.touchedSound[8] = MainActivity.soundPool.load(this, R.raw.hre, 1);

                    //도# 레# 파# 솔# 라# : x값이 ? 이상인 경우 검은건반으로 인식하여 해당 음계의 배열 +10 값을 # 음으로 한다.
                    MainActivity.touchedSound[10] = MainActivity.soundPool.load(this, R.raw.doos, 1);
                    MainActivity.touchedSound[11] = MainActivity.soundPool.load(this, R.raw.res, 1);
                    MainActivity.touchedSound[13] = MainActivity.soundPool.load(this, R.raw.pas, 1);
                    MainActivity.touchedSound[14] = MainActivity.soundPool.load(this, R.raw.sols, 1);
                    MainActivity.touchedSound[15] = MainActivity.soundPool.load(this, R.raw.ras, 1);
                }

                //재생
                for (int w = 0 ; w < ar.size() ; w++) {
                    //Log.e("arlog = " , ar.get(w).toString());

                    //플레이
                    MainActivity.soundPool.play(MainActivity.touchedSound[ar.get(w).getNote()], MainActivity.volume, MainActivity.volume, 0, 0, MainActivity.accel);

                    //대기시간(재생시간)
                    try {
                        Thread.sleep(ar.get(w).getMilisecond());
                        //SystemClock.sleep(ar.get(w).getMilisecond() + (ar.get(w).getMilisecond() / 3));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    //종료
                    MainActivity.soundPool.autoPause();

                    //idle시간
                    try {
                        Thread.sleep(ar.get(w).getIdle());

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }



        });
        thread.start();




        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
