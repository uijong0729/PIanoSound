package settingUI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;

import org.json.JSONException;
import org.json.JSONObject;

import Utility.FileManager;
import me.euijonglee.realrealsound.MainActivity;
import me.euijonglee.realrealsound.R;

public class PianoSetting extends Activity {

    private Button button, cancle, piano_settings_init;
    private SeekBar accelSeekBar, volumeSeekBar;
    private Switch vib;
    private final short TRUE = 1;
    private final short FALSE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pianoset);

        button = (Button) findViewById(R.id.piano_settings_confirm);
        cancle = (Button) findViewById(R.id.piano_settings_reject);
        piano_settings_init = (Button) findViewById(R.id.piano_settings_init);
        vib = (Switch) findViewById(R.id.vib);

        accelSeekBar = (SeekBar) findViewById(R.id.totalAccel);
        volumeSeekBar = (SeekBar) findViewById(R.id.totalVolume);

        //초기화
        //설정정보를 가져옴
        String pConfig = FileManager.readTextFile(FileManager.directory, "pianoSetting.json");
        //Log.e("config", pConfig);

        if(!(pConfig == ""))
        {
            JSONObject pSetting;

            //todo: json파일을 저장해서 불러오도록
            try {
                pSetting = new JSONObject(pConfig);
                accelSeekBar.setProgress((int)((pSetting.getDouble("accel") * 100.0)));
                volumeSeekBar.setProgress((int)((pSetting.getDouble("volume") * 100.0)));
                //Log.e("progressV", (int)(pSetting.getDouble("volume") * 100.0) + "");
                //Log.e("progressA", (int)((pSetting.getDouble("accel") * 100.0)) + "");

            } catch (JSONException e) {
                e.printStackTrace();
                accelSeekBar.setProgress(100);
                volumeSeekBar.setProgress(100);
            }
        }


        //진동세팅 로드
        short setting = Short.parseShort(FileManager.readTextFile(FileManager.directory, "vibconfig.txt"));
        if(setting == 1)
        {
            vib.setChecked(true);
        }
        else {
            vib.setChecked(false);
        }

        //진동 체크 on/off설정
        vib.setOnCheckedChangeListener((CompoundButton compoundButton, boolean b) -> {
            //Toast.makeText(this, b+"", Toast.LENGTH_SHORT).show();
            if(b) {
              MainActivity.isVib  = TRUE;
              FileManager.makeTextFile(FileManager.directory, "vibconfig.txt", TRUE);
            }
            else {
                MainActivity.isVib = FALSE;
                FileManager.makeTextFile(FileManager.directory, "vibconfig.txt", FALSE);
            }
        });

        //확인버튼
        button.setOnClickListener((View view) -> {
                String settingJson = "{volume: " + MainActivity.volume
                                     + ", accel: " + MainActivity.accel + "}";
                //json파일 저장
                FileManager.makeTextFile(FileManager.directory, "pianoSetting.json", settingJson);

            finish();
            }
        );

        //뒤로버튼
        cancle.setOnClickListener((View view) -> {

            finish();
        });

        //설정 초기화 버튼
        piano_settings_init.setOnClickListener((v)->{
            String settingJson = "{volume: 1.0, accel: 1.0}";
            //json파일 저장
            FileManager.makeTextFile(FileManager.directory, "pianoSetting.json", settingJson);
            MainActivity.accel = 1.0f;
            MainActivity.volume = 1.0f;
            accelSeekBar.setProgress(100);
            volumeSeekBar.setProgress(100);

        });

        //키 바
        accelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MainActivity.accel = ((float)i) / 100.0f;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //볼륨 바
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MainActivity.volume = ((float)i) / 100.0f;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
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
}
