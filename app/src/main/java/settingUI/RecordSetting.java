package settingUI;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.File;

import file.FileManager;
import me.euijonglee.realrealsound.R;

public class RecordSetting extends Activity {

    private SeekBar sysvol;
    private Button button;
    private int system_volume;
    AudioManager audio;

    File file = new File(FileManager.directory);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordset);

        //폴더 생성
        FileManager.fileInit(FileManager.directory);


        audio = (AudioManager) getSystemService(AUDIO_SERVICE);
        button = findViewById(R.id.piano_settings_confirm);

        system_volume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        sysvol = findViewById(R.id.sysvol);

        sysvol.setProgress(system_volume);

        button.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                //파일 생성
                FileManager.makeTextFile(FileManager.directory, "config.txt", audio.getStreamVolume(AudioManager.STREAM_MUSIC));

                finish();
            }
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


}
