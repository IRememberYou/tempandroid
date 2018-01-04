package com.example.pinan.tempandroid.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseActivity;
import com.example.pinan.tempandroid.base.SimpleDialogFragment;
import com.example.pinan.tempandroid.home.adapter.PhotoAllAdapter;
import com.example.pinan.tempandroid.utils.PhotoUtil;
import com.example.pinan.tempandroid.utils.RecyclerViewItemClick;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author pinan
 * @date 2018/1/2
 */

public class PictureActivity extends BaseActivity {
    @BindView(R.id.iv_single_pic)
    ImageView mImageView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
    }
    
    /**
     * 获取手机的所有图片
     */
    @OnClick(R.id.tv_all_pic)
    void onAllPicClick() {
        //设置适配器
        PhotoAllAdapter adapter = new PhotoAllAdapter(PhotoUtil.getAllPic(this));
        //简单的 dialog
        SimpleDialogFragment dialog = new SimpleDialogFragment
            .Builder(this)
            .setResId(R.layout.common_recycle_view)
            .setRecycle(R.id.recycle_view, adapter, new GridLayoutManager(this, 2))
            .addOnItemTouchListener(R.id.recycle_view, new RecyclerViewItemClick() {
                @Override
                protected <T extends RecyclerView.Adapter> void onItemClick(T adapter, View view, int position) {
                    List<Object> item = ((PhotoAllAdapter) adapter).getItem(position);
                    String name = (String) item.get(0);
                    Toast.makeText(PictureActivity.this, name, Toast.LENGTH_SHORT).show();
                }
            })
            .build();
        dialog.show();
    }
    
    /**
     * 获取手机相册的单个图片
     */
    @OnClick(R.id.tv_single_pic)
    void onSinglePicClick() {
//        PhotoUtil.selectSinglePic(this, PhotoUtil.REQUEST_CODE_PICK_IMAGE);
        PhotoUtil.selectMorePic(this, PhotoUtil.PICK_IMAGE);
        
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PhotoUtil.REQUEST_CODE_PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        mImageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(this, "err***", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == PhotoUtil.PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                Set<String> categories = data.getCategories();

//                mImageView.getHandler().removeCallbacksAndMessages(null);
                // 判断是否成功。
                // 拿到用户选择的图片路径List：
//                pathList = Album.parseResult(data);
//                mImageView.getHandler().postDelayed(mRunnable, 1000);
            } else if (resultCode == RESULT_CANCELED) {
                // 用户取消选择。
                // 根据需要提示用户取消了选择。
                Toast.makeText(this, "用户取消选择", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (page >= pathList.size()) {
                page = 0;
            }
            System.out.println(pathList.get(page).toString());
            if (mImageView == null) {
                mImageView.getHandler().removeCallbacksAndMessages(null);
                return;
            }
            mImageView.setImageBitmap(BitmapFactory.decodeFile(pathList.get(page)));
            page++;
            mImageView.getHandler().postDelayed(this, 1000);
        }
    };
    
    List<String> pathList = new ArrayList<>();
    private int page = 0;
}