package com.lado.useradminapp.network;

import com.lado.useradminapp.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Endpoints {

    @GET("/photos")
    Call<List<Photo>> getAllPhotos();
}
