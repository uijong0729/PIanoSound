package settingUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Utility.FileManager;
import me.euijonglee.realrealsound.R;
import service.MusicSheetPlayer;

public class MusicSheetSetting extends Activity {

    Button makejson_confirm, makejson_cancle, makejson_guide, makejson_listen;
    EditText text, title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.activity_makesheet);
        super.onCreate(savedInstanceState);

        makejson_confirm =  findViewById(R.id.makejson_confirm);
        makejson_cancle =  findViewById(R.id.canclejson_confirm);
        makejson_guide = findViewById(R.id.makejson_guide);
        makejson_listen = findViewById(R.id.makejson_listen);

        text =  findViewById(R.id.parsingtext);
        title = findViewById(R.id.parsingtitle);

        makejson_listen.setOnClickListener(v ->{
            String temp = FileManager.textToJson(text.getText().toString());

            Intent intent = new Intent(MusicSheetSetting.this, MusicSheetPlayer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("jsonArray", temp);
            startService(intent);


        });

        makejson_guide.setOnClickListener(v ->{
            AlertDialog.Builder guider = new AlertDialog.Builder(this);
            guider.setNegativeButton("닫기", (dialog, i) ->{

            });
            guider.setTitle("음계 작성 규칙");
            guider.setMessage(" - 음의 길이는 띄어쓰기로 표시\n(예 :      도    레     미)\n - 중간 쉼표는 . 로 표시\n\t(예 : . 도  . 레 .. 미)\n - 각 음은 ‘도레미파솔라시’ 한글로 표기\n\t(예 : 도 레 미)\n - !가 붙으면 높은음, #이 붙으면 반음\n \t(예 : 레# 도! )");
            guider.show();
        });

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
            Toast.makeText(this, "저장 되었습니다.", Toast.LENGTH_SHORT).show();
            finish();

        });



    }
}
