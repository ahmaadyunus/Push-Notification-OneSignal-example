package com.example.ahmaadyunus.onesignalexample.api;

import com.example.ahmaadyunus.onesignalexample.model.NotifResponse;
import com.example.ahmaadyunus.onesignalexample.model.Notification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by ahmaadyunus on 22/01/17.
 */

public interface NotificationAPIInterface {
    @POST("https://onesignal.com/api/v1/notifications")
    Call<NotifResponse> createNotification(
            @Header("Authorization") String restAPIkey,
            @Header("Content-Type") String contentType,
            @Body Notification notification);
}
