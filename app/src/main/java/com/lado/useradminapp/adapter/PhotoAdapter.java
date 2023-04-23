package com.lado.useradminapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lado.useradminapp.OnClickUserInterface;
import com.lado.useradminapp.R;
import com.lado.useradminapp.databinding.PhotosDataLayoutBinding;
import com.lado.useradminapp.databinding.UserDataLayoutBinding;
import com.lado.useradminapp.model.Photo;
import com.lado.useradminapp.model.User;

import java.util.List;
import java.util.Objects;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    List<Photo> photoList;
    private Context context;


    public PhotoAdapter(Context context, List<Photo> photoList){
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PhotosDataLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.photos_data_layout, parent, false );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.ViewHolder holder, int position) {

        if (photoList!=null) {
            Photo photo = photoList.get(position);
            String url = photo.getUrl();
            if (!Objects.equals(url, "")) {
                Glide.with(context)
                        .load(url)
                        .placeholder(R.drawable.base_foreground)
                        .into(holder.binding.photos);
            }
            holder.binding.titlePhoto.setText(photo.getTitle());
            holder.binding.descPhoto.setText(photo.getThumbnailUrl());

        }
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        PhotosDataLayoutBinding binding;
        public ViewHolder(@NonNull PhotosDataLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
