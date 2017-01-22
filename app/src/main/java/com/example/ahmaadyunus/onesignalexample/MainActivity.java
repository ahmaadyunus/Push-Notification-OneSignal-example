package com.example.ahmaadyunus.onesignalexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button push_btn = (Button) findViewById(R.id.push_btn);
        push_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push();
            }
        });

    }
    public void push(){
        try {
            String restApiKey ="Basic Zjg1OGU5MzgtZjY4Mi00NjgyLTg2ZTYtZGZlZmEyNzdiNDE3";
            String contentType="application/json";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://private-80e9a-android23.apiary-mock.com/users/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            NotificationAPIInterface notif_api = retrofit.create(NotificationAPIInterface.class);
            Notification notification = new Notification();
            Contents contents = new Contents();
            AdditionalData additionalData = new AdditionalData();
            List<String> incSegments = new ArrayList<>();
            incSegments.add("All");
            contents.setEn("Test notification using Retrofit2");
            additionalData.setActivityToBeOpened("AnotherActivity");
            notification.setAppId("8fcf6acf-7ac1-4c0a-b4e2-5ade701d522d");
            notification.setContents(contents);
            notification.setAdditionalData(additionalData);
            notification.setIncludedSegments(incSegments);
            Call<NotifResponse> pushNotification = notif_api.createNotification(restApiKey,contentType,notification);
            pushNotification.enqueue(new Callback<NotifResponse>() {
                @Override
                public void onResponse(Call<NotifResponse> call, Response<NotifResponse> response) {
                    Log.i("Response Body Id: ",response.body().getId());
                    Log.i("Response Receipents: ",String.valueOf(response.body().getReceipents()));
                }

                @Override
                public void onFailure(Call<NotifResponse> call, Throwable t) {

                }
            });
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
    }

}

