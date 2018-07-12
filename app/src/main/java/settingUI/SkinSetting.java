package settingUI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import me.euijonglee.realrealsound.Piano;
import me.euijonglee.realrealsound.R;

public class SkinSetting extends Activity {

    ImageButton origin1, origin2, origin3;
    Button confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pianoskin);
        origin1 = (ImageButton) findViewById(R.id.origin1);
        origin2 = (ImageButton) findViewById(R.id.origin2);
        origin3 = (ImageButton) findViewById(R.id.origin3);
        confirm = (Button) findViewById(R.id.skin_confirm);

        //기본 값
        switch (Piano.piano1)
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

        /*  loadImg.setOnClickListener((View view) -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);

            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 7);
        });*/

        confirm.setOnClickListener((View view) -> {
            finish();
        });

        origin1.setOnClickListener((View view)-> {
            origin1.setAlpha(100);
            origin2.setAlpha(10);
            origin3.setAlpha(10);
            Piano.piano1 = R.drawable.realpianowithoutblack;
            Piano.piano2 = R.drawable.realpiano;
        });

        origin2.setOnClickListener((View view)-> {
            origin1.setAlpha(10);
            origin2.setAlpha(100);
            origin3.setAlpha(10);
            Piano.piano1 = R.drawable.table;
            Piano.piano2 = R.drawable.table4;
        });

        origin3.setOnClickListener((View view)-> {
            origin3.setAlpha(100);
            origin2.setAlpha(10);
            origin1.setAlpha(10);
            Piano.piano1 = R.drawable.silophone;
            Piano.piano2 = R.drawable.siloponblack;
        });





    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7 && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {

                add.setImageURI(uri);
                origin1.setAlpha(20);
                origin2.setAlpha(20);
                origin3.setAlpha(20);
                add.setAlpha(100);
                add.setId(R.id.getImg);

                Log.e("url좀 찍어보자", uri.toString() );
                Piano.piano1 = add.getResources().getIdentifier(uri.toString(), "content", getPackageName());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/
}



