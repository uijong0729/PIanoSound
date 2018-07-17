package settingUI;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.SeekBar;

import file.FileManager;
import me.euijonglee.realrealsound.R;

public class RecordSetting extends Activity {

    private SeekBar sysvol;
    private Button button, cancle;
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

        system_volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        sysvol = findViewById(R.id.sysvol);

        sysvol.setProgress(system_volume);

        button.setOnClickListener(view -> {
                //파일 생성
                FileManager.makeTextFile(FileManager.directory, "config.txt", audio.getStreamVolume(AudioManager.STREAM_MUSIC));

                finish();
            });

        cancle.setOnClickListener((view) -> {

            finish();
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
