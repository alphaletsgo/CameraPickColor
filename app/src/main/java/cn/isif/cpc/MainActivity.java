package cn.isif.cpc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btPickColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btPickColor = findViewById(R.id.bt_pick_color);
        btPickColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {

            List<String> denied = new ArrayList<String>(); //记录可以再次询问的权限
            List<String> deniedAndNeverAskAgain = new ArrayList<String>(); //记录不能再次授权的权限
            int result;
            for (int index = 0; index < grantResults.length; index++) {
                result = grantResults[index];
                if (result != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[index])) {
                        denied.add(permissions[index]);
                    } else {
                        deniedAndNeverAskAgain.add(permissions[index]);
                    }
                }
            }

            if (denied.isEmpty() && deniedAndNeverAskAgain.isEmpty()) {//获得授权
//                    takePic()
                Intent intent = new Intent(this,PickColorActivity.class);
                startActivity(intent);
            } else {//未获得授权
                if (!denied.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("拍照功能需要您同意相机和定位权限");
                    builder.setCancelable(false);
                    builder.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            requestPermission();
                        }
                    });
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("您可能需要去设置中同意使用相机和位置权限");
                    builder.setCancelable(false);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", getPackageName(), null);
                            intent.setData(uri);
                            startActivityForResult(intent, 1);
                        }
                    });
                    builder.create().show();

                }

            }

        }
    }


    /**
     * 申请权限
     */
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);

    }

}
