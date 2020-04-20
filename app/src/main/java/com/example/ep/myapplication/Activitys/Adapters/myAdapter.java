package com.example.ep.myapplication.Activitys.Adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.example.ep.myapplication.Activitys.Model.State;
import com.example.ep.myapplication.Activitys.SVGUtils.SvgDecoder;
import com.example.ep.myapplication.Activitys.SVGUtils.SvgDrawableTranscoder;
import com.example.ep.myapplication.Activitys.SVGUtils.SvgSoftwareLayerSetter;
import com.example.ep.myapplication.R;

import java.io.InputStream;
import java.util.ArrayList;

import static com.bumptech.glide.Glide.*;

public class myAdapter extends RecyclerView.Adapter<myAdapter.MyViewHolder> {

    String namesData[];
    int imagesData[];
    private Context context;


    private ArrayList<State> allstatesIn;


    public myAdapter(Context ct, ArrayList<State> allstates) {
        context = ct;
        allstatesIn = allstates;

    }

    public ArrayList<State> getAllstatesIn() {
        return allstatesIn;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.text.setText(namesData[position]);
////        holder.pic.setImageResource(imagesData[position]);
        holder.text.setText(allstatesIn.get(position).getName());
        holder.text2.setText(allstatesIn.get(position).getNativeName());
        String userAvatarUrl = allstatesIn.get(position).getFlag();
        Log.d("Flag URL:", userAvatarUrl);

        GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder = Glide.with(context)
                .using(buildStreamModelLoader(Uri.class, context), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .listener(new SvgSoftwareLayerSetter<Uri>());

        requestBuilder.diskCacheStrategy(DiskCacheStrategy.NONE)
                .load(Uri.parse(allstatesIn.get(position).getFlag()))
                .into(holder.pic);
        // Utils.fetchSvg(context, userAvatarUrl, holder.pic);

    }

    @Override
    public int getItemCount() {
        return allstatesIn.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView text2;
        ImageView pic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView3);
            text2 = itemView.findViewById(R.id.countryNameNativeTextView);
            pic = itemView.findViewById(R.id.imageView3);
        }
    }


    public ArrayList<State> custumeFilter(ArrayList<State> input, String word) // for search edit text - filter function
    {
        ArrayList<State> arr = new ArrayList<State>();

        for (State s : input) {
            if (s.getName().toLowerCase().contains(word) || s.getNativeName().toLowerCase().contains(word)) {
                arr.add(s);
            }
        }
        return arr;
    }
}
