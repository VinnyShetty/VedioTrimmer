package com.marolix.videotrimmer;

import android.content.Context;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;




public abstract class VideoPicker extends BottomSheetDialog implements View.OnClickListener {
    protected long lastClickTime = 0;
    TextView camera,gallery;
//    DialogVideoPickerBinding mBinder;

    public VideoPicker(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.dialog_video_picker);
       camera=findViewById(R.id.camera);
        gallery=findViewById(R.id.gallery);
        camera.setOnClickListener(this);
       gallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        preventDoubleClick(view);
        dismiss();
        switch (view.getId()) {
            case R.id.camera:
                onCameraClicked();
                break;
            case R.id.gallery:
                onGalleryClicked();
                break;
        }
    }

    private void preventDoubleClick(View view) {
        /*// preventing double, using threshold of 1000 ms*/
        if (SystemClock.elapsedRealtime() - lastClickTime < 1000) {
            return;
        }
        lastClickTime = SystemClock.elapsedRealtime();
    }

    protected abstract void onCameraClicked();

    protected abstract void onGalleryClicked();
}