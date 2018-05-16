package com.example.roman.socialnetworkandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roman.socialnetworkandroid.data.components.ApplicationComponent;
import com.example.roman.socialnetworkandroid.data.components.DaggerApplicationComponent;
import com.example.roman.socialnetworkandroid.data.managers.DataManager;
import com.example.roman.socialnetworkandroid.data.managers.PreferencesManager;
import com.example.roman.socialnetworkandroid.data.module.ContextModule;
import com.example.roman.socialnetworkandroid.model.UserDto;
import com.example.roman.socialnetworkandroid.retrofit.APIInterface;
import com.example.roman.socialnetworkandroid.util.App;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText password,username;
    ApplicationComponent applicationComponent;
    @Inject
    APIInterface apiInterface;
    PreferencesManager preferencesManager= DataManager.getInstance().getPreferencesManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username =findViewById(R.id.username);
        password=findViewById(R.id.password);
//        App.get(this);
        applicationComponent = DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
        applicationComponent.injectApplication(this);
    }

    public void login(View view) throws IOException {
        UserDto userDto=new UserDto(username.getText().toString(),password.getText().toString());
        Call<String> call= apiInterface.auth(userDto);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_LONG).show();
                    preferencesManager.setAuthToken(response.headers().get("access-token"));
                    preferencesManager.setUsername(username.getText().toString());
//                    preferencesManager.setUserId(response.body().getId());
                    Intent intent=new Intent("com.example.roman.myretrofitexample.MainPageActivity");
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "successful", Toast.LENGTH_LONG).show();
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println(t.getLocalizedMessage());
                System.out.println(t.getCause());
            }
        });
    }
}
