package com.example.pinan.tempandroid.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pinan.tempandroid.R;
import com.example.pinan.tempandroid.base.BaseActivity;
import com.example.pinan.tempandroid.base.SimpleDialogFragment;
import com.example.pinan.tempandroid.home.adapter.PhotoAllAdapter;
import com.example.pinan.tempandroid.utils.PhotoUtil;
import com.example.pinan.tempandroid.utils.RecyclerViewItemClick;

import java.util.List;

import butterknife.OnClick;

/**
 * @author pinan
 * @date 2018/1/2
 */

public class PictureActivity extends BaseActivity {
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
    }
    
    @OnClick(R.id.tv_single_pic)
    void onSinglePicClick() {
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
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PhotoUtil.REQUEST_CODE_PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
            }
        }
    }
}
