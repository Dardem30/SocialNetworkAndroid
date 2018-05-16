package com.example.roman.socialnetworkandroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.roman.socialnetworkandroid.data.components.ApplicationComponent;
import com.example.roman.socialnetworkandroid.data.components.DaggerApplicationComponent;
import com.example.roman.socialnetworkandroid.data.managers.DataManager;
import com.example.roman.socialnetworkandroid.data.module.ContextModule;
import com.example.roman.socialnetworkandroid.model.PhotoList;
import com.example.roman.socialnetworkandroid.model.Photos;
import com.example.roman.socialnetworkandroid.model.User;
import com.example.roman.socialnetworkandroid.retrofit.APIInterface;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainPageActivity extends AppCompatActivity {
    ImageView imageView;
    String username= DataManager.getInstance().getPreferencesManager().getUsername();
    ApplicationComponent applicationComponent;
    @Inject
    APIInterface link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        imageView=(ImageView) findViewById(R.id.mainImg);
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
        Call<User> call=link.userByUsername(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    System.out.println("success");

                }else{
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getCause());
            }
        });
        link.getMainPhoto(21)
                .enqueue(new Callback<PhotoList>() {
                    @Override
                    public void onResponse(Call<PhotoList> call, Response<PhotoList> response) {
                        if(response.isSuccessful()) {
                            System.out.println("rsssssssss");
                            byte[] byteArray=null;
                            for(Photos photos: response.body().getPhotosList()) {
                                if(photos.isIsmain()==true) {
                                    byteArray = photos.getContent();
                                }
                            }
                            Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp, imageView.getWidth(),
                                    imageView.getHeight(), false));
                        }else {
                            System.out.println("rffffffff");
                            System.out.println(response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<PhotoList> call, Throwable t) {
                        System.out.println("rtttttttttttt");
                        System.out.println(t.getMessage());
                        System.out.println(call.request().body());
                        System.out.println(t.getCause().getLocalizedMessage());
                    }
                });
    }

    public void getPassword(View view) {
    }
}
