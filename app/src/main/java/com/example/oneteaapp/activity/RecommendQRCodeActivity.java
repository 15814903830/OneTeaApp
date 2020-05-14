package com.example.oneteaapp.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneteaapp.MainActivity;
import com.example.oneteaapp.R;
import com.example.oneteaapp.utils.ImageUtil;
import com.example.oneteaapp.utils.SharedPrefUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 推荐二维码
 */
public class RecommendQRCodeActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.iv_qb_code)
    ImageView ivQbCode;
    @BindView(R.id.btn_baochun)
    Button btnBaochun;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private static final String tip = "APP需要存储权限用来上传头像";
    private static final int RC_CAMERA_PERM = 123;
    private static final String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA};
    public static void actionStart(Context context, String groupid) {
        Intent intent = new Intent(context, RecommendQRCodeActivity.class);
        intent.putExtra("groupid", groupid);
        context.startActivity(intent);
    }

    private String groupid;
    Bitmap qrBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_qrcode);
        ButterKnife.bind(this);
        tv_title.setText("我要推荐");
        groupid = getIntent().getStringExtra("groupid");
        generate("http://m.yihaominggu.com/web/901zhuce.html?id="+ SharedPrefUtil.getUserInfo().getData().getId(), ivQbCode);
        llReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnBaochun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(RecommendQRCodeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RecommendQRCodeActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    return;
                }
                if (ContextCompat.checkSelfPermission(RecommendQRCodeActivity.this, Manifest.permission.CAMERA) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RecommendQRCodeActivity.this, new String[]{Manifest.permission.CAMERA}, 0);
                    return;
                }

                saveImg(createBitmap(llAll));
            }
        });

    }

    /**
     * 保存图片至本地
     * @param bitmap
     */
    private void saveImg(Bitmap bitmap){
        String fileName = "qr_"+System.currentTimeMillis() + ".jpg";
        boolean isSaveSuccess = ImageUtil.saveImageToGallery(RecommendQRCodeActivity.this, bitmap,fileName);
        if (isSaveSuccess) {
            Toast.makeText(RecommendQRCodeActivity.this, "图片已保存至本地", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RecommendQRCodeActivity.this, "保存图片失败，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 保存图片到相册
     */
    public void saveImageToGallery(Bitmap mBitmap) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        // 首先保存图片
        File appDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsoluteFile();
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getApplication().getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } // 最后通知图库更新

        getApplication().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + "")));


    }

    private void generate(String content, ImageView iv) {
        qrBitmap = CodeUtils.createImage(content,300, 300, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        iv.setImageBitmap(qrBitmap);
    }



    private Bitmap createBitmap(View view) {
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
