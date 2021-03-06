package com.marolix.videotrimmer;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.marolix.videotrimmer.DeepVideoTrimmer;
import com.marolix.videotrimmer.interfaces.OnTrimVideoListener;
import com.marolix.videotrimmer.view.RangeSeekBarView;

import static com.marolix.videotrimmer.Constants.EXTRA_VIDEO_PATH;


public class VideoTrimmerActivity extends BaseActivity implements OnTrimVideoListener {
    private DeepVideoTrimmer mVideoTrimmer;
    TextView textSize, tvCroppingMessage;
    RangeSeekBarView timeLineBar;
    int maxDuration = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_trimmer);

        Intent extraIntent = getIntent();
        String path = "";

        if (extraIntent != null) {
            path = extraIntent.getStringExtra(EXTRA_VIDEO_PATH);
            maxDuration = extraIntent.getIntExtra(MainActivity.VIDEO_TOTAL_DURATION, 100);

        }

        mVideoTrimmer = ((DeepVideoTrimmer) findViewById(R.id.timeLine));
        timeLineBar = (RangeSeekBarView) findViewById(R.id.timeLineBar);
        textSize = (TextView) findViewById(R.id.textSize);
        tvCroppingMessage = (TextView) findViewById(R.id.tvCroppingMessage);

        if (mVideoTrimmer != null && path != null) {
            mVideoTrimmer.setMaxDuration(maxDuration);
            mVideoTrimmer.setOnTrimVideoListener(this);
            mVideoTrimmer.setVideoURI(Uri.parse(path));
        } else {
            showToastLong(getString(R.string.toast_cannot_retrieve_selected_video));
        }
    }

    @Override
    public void getResult(final Uri uri) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvCroppingMessage.setVisibility(View.GONE);
            }
        });
        Constants.croppedVideoURI = uri.toString();
        Intent intent = new Intent();
        intent.setData(uri);
        setResult(RESULT_OK, intent);
        finish();

    }

    @Override
    public void cancelAction() {
        mVideoTrimmer.destroy();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvCroppingMessage.setVisibility(View.GONE);
            }
        });
        finish();
    }
}
