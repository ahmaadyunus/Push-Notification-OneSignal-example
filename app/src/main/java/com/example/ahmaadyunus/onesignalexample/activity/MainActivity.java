package com.example.ahmaadyunus.onesignalexample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ahmaadyunus.onesignalexample.api.NotificationAPIInterface;
import com.example.ahmaadyunus.onesignalexample.R;
import com.example.ahmaadyunus.onesignalexample.model.AdditionalData;
import com.example.ahmaadyunus.onesignalexample.model.Contents;
import com.example.ahmaadyunus.onesignalexample.model.NotifResponse;
import com.example.ahmaadyunus.onesignalexample.model.Notification;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
            String restApiKey ="Basic YOUR_REST_API_KEY_ONESIGNAL_APPLICATION";
            String contentType="application/json";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://onesignal.com/api/v1/notifications/")
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
            notification.setAppId("YOUR_ONESIGNAL_APPID");
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

