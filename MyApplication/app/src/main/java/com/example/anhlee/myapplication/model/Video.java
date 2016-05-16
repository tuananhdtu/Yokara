package com.example.anhlee.myapplication.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by anhlee on 5/9/16.
 */
public class Video {
    public String videoId;
    public String videoTitle;
    public int pitchShift;
    public int duration;

    public Video(String videoId, String videoTitle, int pitchShift, int duration) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.pitchShift = pitchShift;
        this.duration = duration;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public int getPitchShift() {
        return pitchShift;
    }

    public void setPitchShift(int pitchShift) {
        this.pitchShift = pitchShift;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String toJSON(){

        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("videoId", getVideoId());
            jsonObject.put("videoTitle", getVideoTitle());
            jsonObject.put("pitchShift", getPitchShift());
            jsonObject.put("duration", getDuration());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }
}
