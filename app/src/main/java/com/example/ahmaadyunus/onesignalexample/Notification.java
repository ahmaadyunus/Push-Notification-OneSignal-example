package com.example.ahmaadyunus.onesignalexample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ahmaadyunus on 21/01/17.
 */

public class Notification {

    @SerializedName("app_id")
    @Expose
    private String appId;

    @SerializedName("contents")
    @Expose
    private Contents contents;



    @SerializedName("data")
    @Expose
    private AdditionalData additionalData;

    @SerializedName("included_segments")
    @Expose
    private List<String> includedSegments = null;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

    public AdditionalData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalData additionalData) {
        this.additionalData = additionalData;
    }

    public List<String> getIncludedSegments() {
        return includedSegments;
    }

    public void setIncludedSegments(List<String> includedSegments) {
        this.includedSegments = includedSegments;
    }
}
