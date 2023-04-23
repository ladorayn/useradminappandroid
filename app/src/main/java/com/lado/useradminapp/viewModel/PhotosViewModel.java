package com.lado.useradminapp.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lado.useradminapp.model.Photo;
import com.lado.useradminapp.repository.AppRepo;

import java.util.List;

public class PhotosViewModel extends AndroidViewModel {


    private AppRepo appRepo;

    public PhotosViewModel(@NonNull Application application) {
        super(application);
        appRepo = new AppRepo(application);
    }

    public MutableLiveData<List<Photo>> getPhotoAPI() {
        return appRepo.getPhotoAPI();
    }
}
