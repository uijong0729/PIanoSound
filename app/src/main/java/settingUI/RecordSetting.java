package settingUI;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import Utility.FileManager;
import me.euijonglee.realrealsound.R;

public class RecordSetting extends Activity {

    private SeekBar sysvol;
    private Button button, cancle, initialize;
    private int system_volume;
    AudioManager audio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordset);

        //폴더 생성
        FileManager.fileInit(FileManager.directory);


        audio = (AudioManager) getSystemService(AUDIO_SERVICE);
        button = findViewById(R.id.piano_settings_confirm);
        cancle = findViewById(R.id.recorder_settings_systemVolume);
        initialize = findViewById(R.id.system_setting_initialize);

        //시스템 볼륨
        system_volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        sysvol = findViewById(R.id.sysvol);

        //시스템음량 프로그래스바에 현재 음량 반영
        sysvol.setProgress(system_volume);

        //확인
        button.setOnClickListener(view -> {
                //파일 생성
                FileManager.makeTextFile(FileManager.directory, "config.txt", audio.getStreamVolume(AudioManager.STREAM_MUSIC));

                finish();
            });

        //취소
        cancle.setOnClickListener((view) -> {

            finish();
        });

        //동기화
        initialize.setOnClickListener(v ->{
                Toast.makeText(this, "현재 음량에 동기화됩니다.", Toast.LENGTH_SHORT).show();
                sysvol.setProgress(system_volume);
        });


        sysvol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
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
