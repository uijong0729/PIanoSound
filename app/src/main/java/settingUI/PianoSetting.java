package settingUI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import me.euijonglee.realrealsound.MainActivity;
import me.euijonglee.realrealsound.R;

public class PianoSetting extends Activity {

    private Button button, cancle;
    private SeekBar accelSeekBar, volumeSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pianoset);

        button = (Button) findViewById(R.id.piano_settings_confirm);
        cancle = (Button) findViewById(R.id.piano_settings_reject);

        accelSeekBar = (SeekBar) findViewById(R.id.totalAccel);
        volumeSeekBar = (SeekBar) findViewById(R.id.totalVolume);

        button.setOnClickListener((View view) -> {
                finish();
            }
        );

        cancle.setOnClickListener((View view) -> {
            MainActivity.accel = 1.0f;
            MainActivity.volume = 1.0f;
            finish();
        });

        accelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                MainActivity.accel = ((float)i + 50) / 100.0f;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
