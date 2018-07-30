package settingUI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import Utility.FileManager;
import me.euijonglee.realrealsound.R;

public class MusicSheetSetting extends Activity {

    Button makejson_confirm, makejson_cancle;
    EditText text, title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.activity_makesheet);
        super.onCreate(savedInstanceState);

        makejson_confirm =  findViewById(R.id.makejson_confirm);
        makejson_cancle =  findViewById(R.id.canclejson_confirm);
        text =  findViewById(R.id.parsingtext);
        title = findViewById(R.id.parsingtitle);

        makejson_cancle.setOnClickListener(view -> {
            finish();
        });

        makejson_confirm.setOnClickListener(view -> {
            //Log.e("title = ", title.getText().toString());
            //Log.e("text = ", text.getText().toString());
            //String value = text.getText().toString();

            String result = FileManager.textToJson(text.getText().toString());
            //Log.e("getJson = ", result);
            FileManager.makeTextFile(FileManager.jsonDirectory, "/" + title.getText().toString() + ".json", result);
            finish();

        });



    }
}
