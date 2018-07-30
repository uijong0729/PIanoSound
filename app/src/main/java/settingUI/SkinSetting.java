package settingUI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import me.euijonglee.realrealsound.MainActivity;
import me.euijonglee.realrealsound.R;

public class SkinSetting extends Activity {

    ImageButton origin1, origin2, origin3;
    Button confirm, cancle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pianoskin);
        origin1 = (ImageButton) findViewById(R.id.origin1);
        origin2 = (ImageButton) findViewById(R.id.origin2);
        origin3 = (ImageButton) findViewById(R.id.origin3);
        confirm = (Button) findViewById(R.id.skin_confirm);
        cancle = (Button) findViewById(R.id.pianoSkin_setting_cancle);

        //기본 값
        switch (MainActivity.piano1)
        {
            case R.drawable.realpianowithoutblack:
                origin1.setAlpha(100);
                origin2.setAlpha(10);
                origin3.setAlpha(10);
                break;
            case R.drawable.table:
                origin1.setAlpha(10);
                origin2.setAlpha(100);
                origin3.setAlpha(10);
                break;
            case R.drawable.silophone:
                origin3.setAlpha(100);
                origin2.setAlpha(10);
                origin1.setAlpha(10);
                break;
            default:
                origin1.setAlpha(100);
                origin2.setAlpha(10);
                origin3.setAlpha(10);
        }

        confirm.setOnClickListener(view -> {
            finish();
        });

        cancle.setOnClickListener(v -> {
            finish();
        });

        origin1.setOnClickListener((View view)-> {
            origin1.setAlpha(100);
            origin2.setAlpha(10);
            origin3.setAlpha(10);


            if(MainActivity.piano1 != R.drawable.realpianowithoutblack && MainActivity.piano1 != R.drawable.table) {

                soundPoolInit();
                loadPiano();


            }

            MainActivity.piano1 = R.drawable.realpianowithoutblack;
            MainActivity.piano2 = R.drawable.realpiano;

        });

        origin2.setOnClickListener((View view)-> {
            origin1.setAlpha(10);
            origin2.setAlpha(100);
            origin3.setAlpha(10);

            if(MainActivity.piano1 != R.drawable.realpianowithoutblack && MainActivity.piano1 != R.drawable.table) {

                soundPoolInit();
                loadPiano();

            }

            MainActivity.piano1 = R.drawable.table;
            MainActivity.piano2 = R.drawable.table4;


        });

        origin3.setOnClickListener((View view)-> {
            origin3.setAlpha(100);
            origin2.setAlpha(10);
            origin1.setAlpha(10);

            //도 레 미 파 솔 라 시 도
            if(MainActivity.piano1 != R.drawable.silophone)
            {

                soundPoolInit();
                loadSilophone();

            }


            MainActivity.piano1 = R.drawable.silophone;
            //MainActivity.piano2 = R.drawable.silophone;




        });





    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void soundPoolInit(){
        //Log.e("사운드 풀 초기화", MainActivity.soundPool.toString());
        if(MainActivity.soundPool != null)
        {
           // Log.e("사운드 풀 릴리즈", MainActivity.soundPool.toString());
            MainActivity.soundPool.release();
            MainActivity.createSoundPool();
        }
        else{
           MainActivity.createSoundPool();
        }
        //Log.e("사운드 풀 초기화 완료", MainActivity.soundPool.toString());
    }


    private void loadPiano(){
        MainActivity.touchedSound[0] = MainActivity.soundPool.load(this, R.raw.doo, 1);
        MainActivity.touchedSound[1] = MainActivity.soundPool.load(this, R.raw.re, 1);
        MainActivity.touchedSound[2] = MainActivity.soundPool.load(this, R.raw.mi, 1);
        MainActivity.touchedSound[3] = MainActivity.soundPool.load(this, R.raw.pa, 1);
        MainActivity.touchedSound[4] = MainActivity.soundPool.load(this, R.raw.sol, 1);
        MainActivity.touchedSound[5] = MainActivity.soundPool.load(this, R.raw.ra, 1);
        MainActivity.touchedSound[6] = MainActivity.soundPool.load(this, R.raw.si, 1);
        MainActivity.touchedSound[7] = MainActivity.soundPool.load(this, R.raw.hdo, 1);
    }

    private void loadSilophone(){
        MainActivity.touchedSound[0] = MainActivity.soundPool.load(this, R.raw.sdo, 1);
        MainActivity.touchedSound[1] = MainActivity.soundPool.load(this, R.raw.sre, 1);
        MainActivity.touchedSound[2] = MainActivity.soundPool.load(this, R.raw.smi, 1);
        MainActivity.touchedSound[3] = MainActivity.soundPool.load(this, R.raw.spa, 1);
        MainActivity.touchedSound[4] = MainActivity.soundPool.load(this, R.raw.ssol, 1);
        MainActivity.touchedSound[5] = MainActivity.soundPool.load(this, R.raw.sla, 1);
        MainActivity.touchedSound[6] = MainActivity.soundPool.load(this, R.raw.ssi, 1);
        MainActivity.touchedSound[7] = MainActivity.soundPool.load(this, R.raw.ssdo, 1);
    }

}



