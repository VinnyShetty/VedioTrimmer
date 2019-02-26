package com.marolix.videotrimmer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> al;
    public CustomAdapter(Context context, ArrayList<String> al)
    {
     this.context=context;
     this.al=al;
    }
    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        AppCompatImageView image=view.findViewById(R.id.trimvideos);
        ImageView play=view.findViewById(R.id.playicon);
        Bitmap thumb = ThumbnailUtils.createVideoThumbnail( Environment.getExternalStorageDirectory().getPath()+"/trim videos/" +al.get(position),
                MediaStore.Images.Thumbnails.FULL_SCREEN_KIND);
        Glide.with(context)
                .load(new File(Environment.getExternalStorageDirectory().getPath()+"/trim videos/" +al.get(position))).thumbnail(0.5f)
                .into(image);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Trimvideo.class);
                i.putExtra("url",Environment.getExternalStorageDirectory().getPath()+"/trim videos/" +al.get(position));
               context.startActivity(i);
            }
        });
        return view;
    }
}
