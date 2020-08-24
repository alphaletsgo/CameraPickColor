package cn.isif.cpc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import cn.isif.cpc.core.CameraView;
import cn.isif.cpc.core.OnColorStatusChange;
import cn.isif.cpc.core.jni.ImageUtilEngine;

public class PickColorActivity extends AppCompatActivity {
    private String TAG = "PickColorActivity";
    private TextView colorText;
    private CameraView mSelfView;
    static ImageUtilEngine imageEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pick_color);
        imageEngine = new ImageUtilEngine();
        mSelfView = (CameraView) findViewById(R.id.self_view);
        colorText = (TextView) findViewById(R.id.color);
        mSelfView.setOnColorStatusChange(new OnColorStatusChange() {

            @Override
            public void onColorChange(int color) {
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);
                Log.w(TAG, "R:" + r + " G:" + g + " B:" + b);
                colorText.setText("R:" + r + " G:" + g + " B:" + b);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSelfView.invalidate();
    }

    public static ImageUtilEngine getImageEngine() {
        return imageEngine;
    }
}